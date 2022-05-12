package com.example.picsumimage.pagination

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.picsumimage.R
import com.example.picsumimage.model.PicSumModel
// Implement Paging Data Adapter
class ItemPagingAdapter( private val context: Context) : PagingDataAdapter<PicSumModel, ItemPagingAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //Set PicSum Image Layout
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.picsum_image_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Initialize  Paging Model
        val model: PicSumModel? = getItem(position)

        // Set author name on Text View
        if (model != null) {
            holder.author.text = model.author
        }
        // Set Image on Image View
        if (model != null) {
            Glide.with(context)
                 .load(model.download_url)
                 .placeholder(R.drawable.animated_loadbar)
                 .error(R.drawable.ic_launcher_foreground)
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(holder.picsumimage)
        }
    }

        object Diff : DiffUtil.ItemCallback<PicSumModel>() {
            override fun areItemsTheSame(oldItem: PicSumModel, newItem: PicSumModel): Boolean {
                return oldItem.download_url == newItem.download_url
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PicSumModel, newItem: PicSumModel): Boolean {
                // Return Contents same
                return oldItem === newItem
            }
        }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Initialize Variable
        var picsumimage: ImageView = itemView.findViewById(R.id.picsum_image)
        var author: TextView = itemView.findViewById(R.id.picsum_author)
    }


}