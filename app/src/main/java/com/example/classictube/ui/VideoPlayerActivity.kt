package com.example.classictube.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.classictube.databinding.ActivityVideoPlayerBinding
import com.norulab.exofullscreen.MediaPlayer
import com.norulab.exofullscreen.preparePlayer
import com.norulab.exofullscreen.setSource

class VideoPlayerActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        url = intent.getStringExtra("URL Video").toString()
        MediaPlayer.initialize(applicationContext)
        MediaPlayer.exoPlayer?.preparePlayer(binding.playerViewFullscreen, true)
        MediaPlayer.exoPlayer?.setSource(applicationContext, url)
        MediaPlayer.startPlayer()
    }

    override fun onPause() {
        super.onPause()
        MediaPlayer.pausePlayer()
    }


    override fun onStop() {
        MediaPlayer.stopPlayer()
        super.onStop()
    }
}