package com.test.app.net.data.response

import com.google.gson.annotations.SerializedName

data class Album(
    val id: Int,
    val title: String,
    @SerializedName("cover_medium") val cover: String
)