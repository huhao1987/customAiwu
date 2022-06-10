package hh.game.customaiwuclient.Views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.ViewModelProvider
import com.anggrayudi.storage.SimpleStorageHelper
import com.anggrayudi.storage.extension.openOutputStream
import hh.game.customaiwuclient.PermissionUtils
import hh.game.customaiwuclient.VM.MainViewModel
import hh.game.customaiwuclient.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private val storageHelper = SimpleStorageHelper(this)
    private lateinit var binding:ActivityMainBinding
    private var f:DocumentFile?=null
    private val PUNCT_SYMBOLS = Pattern.compile("[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var a=getFromAssets("gamelist")
        var viewmodel=ViewModelProvider(this).get(MainViewModel::class.java)
        storageHelper.openFolderPicker()
        binding.searchgamecodebtn.setOnClickListener {
            if(!binding.gamecodesearch.text.equals(""))
                viewmodel.getDetail(binding.gamecodesearch.text.toString())?.observe(this,{
                    when(it.code){
                        0->binding.showgamecodedetail.text="${it.data?.emuId} ${it.data?.packageName} ${it.data?.title}"
                        1->binding.showgamecodedetail.text=it.message!!
                        500->binding.showgamecodedetail.text=it.message!!
                    }
                })
        }
        binding.searchallcodebtn.setOnClickListener {
            viewmodel.getAllSearch(a!!).observe(this,{
                it.forEach {
                        var cheatdetail=it.aiWuOnlineCheat
                    cheatdetail?.apply {
                        if(this.data?.aiwuCheat!=null) {
                            var cheat=""
                            this.data!!.aiwuCheat.
                            createCheatFile(
                                createFolder(f!!, removePunctuations(it.title!!))!!,
                                it.packageName!!,
                                "testing"
                            )
                        }
                    }

                }
            })
        }
        storageHelper.onFolderSelected={
            requestCode, folder ->
            f=folder
        }
        binding.searchcodebtn.setOnClickListener {
            if(!binding.gamesearch.text.equals(""))
                viewmodel.getSearch(binding.gamesearch.text.toString())?.observe(this,{
                    when(it.code){
                        0->{
                            it.data?.apply {
                                if(this.size>0) {
                                    this[0]?.let {
                                        binding.showgamedetail.text ="${it.title} ${it.emuId}"

                                    }
                                }
                            }
                        }

                    }
                })
        }
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