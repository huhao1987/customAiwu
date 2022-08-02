package hh.game.customaiwuclient.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hh.game.customaiwuclient.EmuType
import hh.game.customaiwuclient.GlideApp
import hh.game.customaiwuclient.Models.SearchResult
import hh.game.customaiwuclient.databinding.RowGamedetailBinding

class GameDetailAdapter(var context:Context, list: List<SearchResult.Data?>, var onClickListener: onClickListener?=null):
    RecyclerView.Adapter<GameDetailAdapter.ViewHolder>() {
    private var gamelist=list
    inner class ViewHolder(binding: RowGamedetailBinding) : RecyclerView.ViewHolder(binding.root) {
        val cover=binding.cover
        val title=binding.title
        val lan=binding.lan
        val gameType=binding.gameType
        val copylinkbtn=binding.copylinkbtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowGamedetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var gamedetail=gamelist[position]
        holder.title.text=gamedetail?.title
        GlideApp.with(context)
            .load(gamedetail?.icon)
            .into(holder.cover)
        holder.itemView.setOnClickListener {
            onClickListener?.onclick(position,gamedetail)
        }
        holder.lan.text=gamedetail?.language
        holder.gameType.text=EmuType.getType(gamedetail?.classType?.toInt()?:0)
        holder.copylinkbtn.setOnClickListener {
            var cm=context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            var copyData=ClipData.newPlainText("downloadUrl",EmuType.getCompleteUrl(gamedetail?.fileLink))
            cm.setPrimaryClip(copyData)
            Toast.makeText(context,"下载链接复制成功",Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount() = gamelist.size

    fun updateList(list: List<SearchResult.Data?>){
        gamelist=gamelist+list
        notifyDataSetChanged()
    }
}
interface onClickListener{
    fun onclick(position: Int,data: SearchResult.Data?)
}