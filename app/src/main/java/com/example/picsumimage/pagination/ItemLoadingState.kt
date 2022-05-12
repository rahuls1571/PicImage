package com.example.picsumimage.pagination

import android.app.ActivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumimage.R
import com.example.picsumimage.databinding.ActivityMainBinding
import com.example.picsumimage.databinding.LoadingLayoutBinding
// Implements LoadStateAdapter
class ItemLoadingState constructor(private val retry : ()->Unit) : LoadStateAdapter<ItemLoadingState.LoadingViewHodler>() {


    override fun onBindViewHolder(holder: LoadingViewHodler, loadState: LoadState) {

        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHodler {
        // Set Loading Layout
        return LoadingViewHodler(LoadingLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false),retry)
    }


    class LoadingViewHodler(private val  binding: LoadingLayoutBinding, retry: () -> Unit) : RecyclerView.ViewHolder(binding.root){
        init {
            // Set Option on Retry Button we click!
            binding.btRetry.setOnClickListener{
                retry()
            }
        }

        // Bind function
        fun bind(loadState: LoadState){
           // SET Visible of Button , Progressbar, TextView
            binding.apply {
              progressCircular.isVisible = loadState is LoadState.Loading
              btRetry.isVisible = loadState !is LoadState.Loading
              tvError.isVisible = loadState !is LoadState.Loading
          }
        }

    }

}