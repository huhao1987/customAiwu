package hh.game.customaiwuclient.Views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggrayudi.storage.SimpleStorageHelper
import com.anggrayudi.storage.extension.openOutputStream
import com.blankj.utilcode.util.KeyboardUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import hh.game.customaiwuclient.EmuType
import hh.game.customaiwuclient.Models.AiWu.AiWuOnlineCheat
import hh.game.customaiwuclient.Models.AiWu.UserCheat
import hh.game.customaiwuclient.Models.SearchResult
import hh.game.customaiwuclient.PermissionUtils
import hh.game.customaiwuclient.VM.MainViewModel
import hh.game.customaiwuclient.adapters.GameDetailAdapter
import hh.game.customaiwuclient.adapters.SpinerAdapter
import hh.game.customaiwuclient.adapters.onClickListener
import hh.game.customaiwuclient.databinding.ActivityMainBinding
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private val storageHelper = SimpleStorageHelper(this)
    private lateinit var binding:ActivityMainBinding
    private var f:DocumentFile?=null
    private val PUNCT_SYMBOLS = Pattern.compile("[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]")
    private var adapter:GameDetailAdapter?=null
    private var page=1
    private var keyWord:String?=null
    //All type
    private var gameType=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var a=getFromAssets("gamelist")
        var viewmodel=ViewModelProvider(this).get(MainViewModel::class.java)
//        storageHelper.openFolderPicker()
        binding.searchgamecodebtn.setOnClickListener {
            if(!binding.gamecodesearch.text.equals(""))
                viewmodel.getDetail(binding.gamecodesearch.text.toString())?.observe(this,{
                    when(it.code){
                        0->{binding.showgamecodedetail.text="${it.data?.emuId} ${it.data?.packageName} ${it.data?.title}"
                            }
                        1->binding.showgamecodedetail.text=it.message!!
                        500->binding.showgamecodedetail.text=it.message!!
                    }
                })
        }
        binding.searchallcodebtn.setOnClickListener {
            binding.loading.visibility= View.VISIBLE
            viewmodel.getAllSearch(a!!).observe(this,{
                it.forEach {

                        var cheatdetail=it.aiWuOnlineCheat
                    cheatdetail?.apply {
                        var processdata=processData(this.data)
                        if(!processdata.equals("")){
                            createCheatFile(
                                createFolder(f!!, removePunctuations(it.title!!))!!,
                                it.packageName!!,
                                processdata
                            )
                        }
                    }
                }
                binding.loading.visibility=View.GONE
            })
        }
        storageHelper.onFolderSelected={
            requestCode, folder ->
            f=folder
        }

        binding.typeselect.attachDataSource(EmuType.gameTypelist)
        binding.typeselect.setOnSpinnerItemSelectedListener(object:OnSpinnerItemSelectedListener{
            override fun onItemSelected(
                parent: NiceSpinner?,
                view: View?,
                position: Int,
                id: Long
            ) {
                gameType=EmuType.gameTypelist.get(position).second
            }

        })

        binding.refreshLayout.let {
            it.setRefreshFooter(ClassicsFooter(this))
            it.setOnLoadMoreListener { refreshlayout ->
                KeyboardUtils.hideSoftInput(this)
                viewmodel.getSearch(keyWord!!,page,gameType)?.observe(this,{
                    when(it.code){
                        0->{
                            it.data?.apply {
                                if(this.size>0) {
                                    adapter?.updateList(this)
                                    page++
                                }
                            }
                        }
                    }
                    refreshlayout.finishLoadMore(true)
                })
            }
        }
        binding.searchcodebtn.setOnClickListener {
            if(!binding.gamesearch.text.equals("")){
                keyWord=binding.gamesearch.text.toString()
                viewmodel.getSearch(keyWord!!,page,gameType)?.observe(this,{
                    when(it.code){
                        0->{
                            it.data?.apply {
                                if(this.size>0) {
                                    adapter= GameDetailAdapter(this@MainActivity,this,object:onClickListener{
                                        override fun onclick(position: Int, data: SearchResult.Data?) {

                                        }
                                    })

                                    binding.gamelist.layoutManager=LinearLayoutManager(this@MainActivity)
                                    binding.gamelist.adapter=adapter
                                    page++
                                }
                                else {
                                    adapter?.updateList(ArrayList())
                                    Toast.makeText(this@MainActivity,"没有搜索到游戏",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    private fun covertData(s:String,userCheat: AiWuOnlineCheat.Data.AiwuCheat?):String{
        var str=s
        userCheat?.apply {
            if (code != null) {
                str += "[${userCheat.title}]\n"
                var cheatlist = code!!.split(",")
                cheatlist.forEach {
                    str += it.replace("-", " ") + "\n"
                }

            }
        }
        return str
    }
    private fun covertData(s:String,userCheat: AiWuOnlineCheat.Data.UserCheat?):String{
        var str=s
        userCheat?.apply {
            if (code != null) {
                str += "[${userCheat.title}]\n"
                var cheatlist = code!!.split(",")
                cheatlist.forEach {
                    str += it.replace("-", " ") + "\n"
                }

            }
        }
        return str
    }
    private fun processData(data:AiWuOnlineCheat.Data?):String{
        if (data != null) {
            if (data!!.userCheat != null || data!!.aiwuCheat != null) {
                var str = "*整理自爱吾游戏宝盒，3ds百度贴吧\n"
                data.aiwuCheat?.forEach {
                    str=covertData(str,it)
                }
                data.userCheat?.forEach {
                    str=covertData(str,it)
                }
                return str
            } else {
                return ""
            }
        } else
            return super.toString()
    }
    private fun createFolder(basefolder:DocumentFile,name:String):DocumentFile?= basefolder.createDirectory(name)

    private fun createCheatFile(folder:DocumentFile,filename:String,content:String){
        var newfile= folder.createFile("text/plain", filename)
        newfile?.uri?.openOutputStream(this)?.apply {
            try {
                write(content.toByteArray())
            }
            catch (e: IOException){

            }
            close()
        }
    }
    fun removePunctuations(source: String?): String {
        return PUNCT_SYMBOLS.matcher(source).replaceAll("")
    }

    fun getFromAssets(fileName: String?): ArrayList<String>? {
        try {
            val inputReader = InputStreamReader(resources.assets.open(fileName!!))
            val bufReader = BufferedReader(inputReader)
            var tempArrayList=ArrayList<String>()
            var line: String? = ""
            while (bufReader.readLine().also { line = it } != null)
                line?.apply {
                    tempArrayList.add(this)
                }
            return tempArrayList
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
    override fun onSaveInstanceState(outState: Bundle) {
        storageHelper.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        storageHelper.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        storageHelper.storage.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        storageHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}