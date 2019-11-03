package net.scadsdnd.imusicsurfer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// RecycleView Adapter.
// https://developer.android.com/guide/topics/ui/layout/recyclerview
class WikiRcVAdapt(private val myDataset: Array<WikipediaModel.Article>):
    RecyclerView.Adapter<WikiRcVAdapt.MyViewHolder>() {

    class MyViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataset[position].title
    }

    override fun getItemCount() = myDataset.size
}