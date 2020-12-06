package com.example.recyclermvvmapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.recyclermvvmapp.R
import com.example.recyclermvvmapp.models.Row

class DetailsAdapter :
    RecyclerView.Adapter<DetailsAdapter.MyViewHolder>() {
    private var results: List<Row> = ArrayList()
    fun setResults(list: List<Row>) {
        this.results = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        internal var tvDescription: TextView = view.findViewById(R.id.tvDescription)
        internal var ivPreview: ImageView = view.findViewById(R.id.ivPreview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val rowItem = results[position]
        if (rowItem.title.isNullOrEmpty()) {
            holder.tvTitle.text = ""
        } else {
            holder.tvTitle.text = rowItem.title
        }

        if (rowItem.description.isNullOrEmpty()) {
            holder.tvDescription.text = ""
        } else {
            holder.tvDescription.text = rowItem.description
        }

        if (rowItem.imageHref.isNullOrEmpty()) {
            holder.ivPreview.visibility = View.GONE
        } else {
            holder.ivPreview.visibility = View.VISIBLE
            Glide.with(holder.ivPreview)
                .load(rowItem.imageHref)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .dontAnimate()
                .dontTransform()
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivPreview)
        }

    }

    override fun getItemCount(): Int {
        return results.size
    }
}