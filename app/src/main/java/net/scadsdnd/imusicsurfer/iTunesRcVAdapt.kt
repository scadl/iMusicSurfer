package net.scadsdnd.imusicsurfer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class iTunesRcVAdapt(private val myDataset: Array<iTunesModel.Album>):
    RecyclerView.Adapter<iTunesRcVAdapt.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(model = myDataset[position])
        holder.itemView.setOnClickListener{
            //Toast.makeText(holder.itemView.context, myDataset[position].collectionId.toString(), Toast.LENGTH_SHORT)
            Log.e("---", myDataset[position].collectionId.toString())
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        private val tvDescr = itemView.findViewById<TextView>(R.id.textViewDescription)
        private val tvPrice = itemView.findViewById<TextView>(R.id.textViewPrice)
        private val ivCover = itemView.findViewById<ImageView>(R.id.imageViewCover)

        fun bind(model: iTunesModel.Album){
            tvTitle.text = model.collectionName
            tvDescr.text = model.copyright
            tvPrice.text = "Price: " + model.collectionPrice.toString() +" "+ model.currency
            Glide.with(itemView.context).load(model.artworkUrl100).into(ivCover)
        }
    }

    override fun getItemCount():Int{
        return myDataset.count()
    }
}