package com.example.classictube.model

import java.io.Serializable

data class PlayListVideo(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val items: List<ListVideo>
): Serializable