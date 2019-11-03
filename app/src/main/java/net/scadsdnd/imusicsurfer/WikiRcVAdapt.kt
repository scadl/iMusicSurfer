package net.scadsdnd.imusicsurfer

import android.app.LauncherActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// RecycleView Adapter.
// https://developer.android.com/guide/topics/ui/layout/recyclerview
class WikiRcVAdapt(private val myDataset: Array<WikipediaModel.Article>):
    RecyclerView.Adapter<WikiRcVAdapt.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return MyViewHolder(textView)
    }

    class MyViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataset[position].title
        //holder.bind(myDataset.get(position))
    }

    override fun getItemCount() = myDataset.size
}