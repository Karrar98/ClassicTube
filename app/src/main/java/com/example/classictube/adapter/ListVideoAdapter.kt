package com.example.classictube.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.classictube.R
import com.example.classictube.databinding.ItemListVideoBinding
import com.example.classictube.interfaces.ListVideoInterface
import com.example.classictube.model.ListVideo

class ListVideoAdapter(private val listVideo: List<ListVideo>,
private val listVideoInterface: ListVideoInterface): RecyclerView.Adapter<ListVideoAdapter.ListVideoViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVideoViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_video, parent, false)
        return ListVideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListVideoViewHolder, position: Int) {
        val currentList = listVideo[position]
        holder.binding.apply {
            Glide.with(context)
                .load(currentList.art)
                //.centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(imageFilm)
            titleFilm.text = currentList.title
            descriptionFilm.text = currentList.description
            durationFilm.text = currentList.duration.toString()
            yearFilm.text = currentList.year.toString()
            listItem.setOnClickListener { listVideoInterface.onClickVideo(currentList.url) }
        }
    }

    override fun getItemCount() = listVideo.size

    class ListVideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemListVideoBinding.bind(itemView)
    }
}