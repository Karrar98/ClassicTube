package com.example.classictube.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.classictube.R
import com.example.classictube.databinding.ItemPlayListVideoBinding
import com.example.classictube.interfaces.PlayListVideoInterface
import com.example.classictube.model.PlayListVideo

class PlayListVideoAdapter(private val playListVideo: List<PlayListVideo>,
private val playListVideoInterface: PlayListVideoInterface):
    RecyclerView.Adapter<PlayListVideoAdapter.PlayListVideoViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListVideoViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_play_list_video, parent, false)
        return PlayListVideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayListVideoViewHolder, position: Int) {
        val currentPlayList = playListVideo[position]
        holder.binding.apply {
            Glide.with(context)
                .load(currentPlayList.image)
                .centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(imagePlayListVideo)
            titlePlayList.text = currentPlayList.title
            descriptionPlayList.text = currentPlayList.description
            listItem.setOnClickListener { playListVideoInterface.onClickList(currentPlayList) }
        }
    }

    override fun getItemCount() = playListVideo.size

    class PlayListVideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemPlayListVideoBinding.bind(itemView)
    }
}