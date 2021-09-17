package com.example.classictube.model

import java.io.Serializable

data class ListVideo(
    val id: String,
    val year: Int,
    val ratings: List<String>?,
    val duration: Int,
    val title: String,
    val director: String,
    val description: String,
    val url: String,
    val art: String
): Serializable