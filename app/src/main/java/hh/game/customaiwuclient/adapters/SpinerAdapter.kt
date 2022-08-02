package hh.game.customaiwuclient.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import hh.game.customaiwuclient.R

class SpinerAdapter(var context: Context,var list:ArrayList<Pair<String,Int>>): BaseAdapter() {
    private var mViewHolder: ViewHolder? = null

    override fun getCount(): Int =list.size

    override fun getItem(p0: Int): Any =p0

    override fun getItemId(p0: Int): Long =p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view=p1
        if(view==null) {
            view = View.inflate(context, R.layout.row_spinner, null)
            var mViewHolder=ViewHolder(view)
            mViewHolder.type=view!!.findViewById(R.id.type)
            view.setTag(mViewHolder)
        }
        else mViewHolder=view!!.getTag() as ViewHolder

        mViewHolder?.type?.text=list.get(p0).first
        return view
    }
    internal class ViewHolder(view:View){
        var type: TextView=view.findViewById(R.id.type)
    }

}