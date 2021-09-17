package com.example.classictube.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.denzcoskun.imageslider.models.SlideModel
import com.example.classictube.adapter.PlayListVideoAdapter
import com.example.classictube.databinding.ActivityMainBinding
import com.example.classictube.interfaces.PlayListVideoInterface
import com.example.classictube.model.Feed
import com.example.classictube.model.ListVideo
import com.example.classictube.model.PlayListVideo
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), Callback, PlayListVideoInterface {

    lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private var feed: Feed? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        initRequest()
    }

    private fun initImageSlider() {
        val slideModel= arrayListOf<SlideModel>()
        feed?.backgrounds?.forEach { imageUrl ->
            slideModel.add(SlideModel(imageUrl, "Classic Image"))
        }
        binding.imageSlider.setImageList(slideModel, true)
    }

    private fun initRecyclerView() {
        val adapterVideo = feed?.let { PlayListVideoAdapter(it.feedList, this) }
        binding.recyclerViewVideo.adapter = adapterVideo
    }

    private fun initRequest() {
        val request = makeRequest()
        client.newCall(request).enqueue(this)
    }

    private fun makeRequest(): Request {
//        val urls = "https://raw.githubusercontent.com/Bareq-altaamah/mock/main/classic.json"
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("raw.githubusercontent.com")
            .addPathSegment("Bareq-altaamah")
            .addPathSegment("mock")
            .addPathSegment("main")
            .addPathSegment("classic.json")
            .build()

        return Request.Builder().url(url).build()
    }

    override fun onFailure(call: Call, e: IOException) {

    }

    override fun onResponse(call: Call, response: Response) {
        feed = Gson().fromJson(response.body?.string().toString(), Feed::class.java)
        runOnUiThread {
            initImageSlider()
            initRecyclerView()
        }

    }

    override fun onClickList(playListVideo: PlayListVideo) {
        val intent = Intent(this@MainActivity, ListVideoActivity::class.java)
        intent.putExtra("PlayListVideo", playListVideo)
        startActivity(intent)
    }
}