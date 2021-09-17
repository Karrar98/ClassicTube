package com.example.classictube.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Feed(
    @SerializedName("feed") val feedList: List<PlayListVideo>,
    val backgrounds: List<String>
): Serializable