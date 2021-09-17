package com.example.classictube.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.classictube.adapter.ListVideoAdapter
import com.example.classictube.databinding.ActivityListVideoBinding
import com.example.classictube.interfaces.ListVideoInterface
import com.example.classictube.model.PlayListVideo

class ListVideoActivity : AppCompatActivity(), ListVideoInterface {

    lateinit var binding: ActivityListVideoBinding
    lateinit var listVideo: PlayListVideo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        listVideo = intent.getSerializableExtra("PlayListVideo") as PlayListVideo
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapterVideo = ListVideoAdapter(listVideo.items, this)
        binding.recyclerViewVideo.adapter = adapterVideo
    }

    override fun onClickVideo(url: String) {
        val intent = Intent(this@ListVideoActivity, VideoPlayerActivity::class.java)
        intent.putExtra("URL Video", url)
        startActivity(intent)
    }
}