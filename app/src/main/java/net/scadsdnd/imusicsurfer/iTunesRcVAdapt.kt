package net.scadsdnd.imusicsurfer

import android.app.LauncherActivity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.http.Url
import java.net.HttpURLConnection
import java.net.URISyntaxException
import java.net.URL

// RecycleView Adapter.
// https://developer.android.com/guide/topics/ui/layout/recyclerview
class iTunesRcVAdapt(private val myDataset: Array<iTunesModel.Album>):
    RecyclerView.Adapter<iTunesRcVAdapt.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.textView.text = myDataset[position].collectionName
        holder.bind(model = myDataset[position])
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