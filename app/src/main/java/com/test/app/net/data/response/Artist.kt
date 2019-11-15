package com.test.app.net.data.response

import com.google.gson.annotations.SerializedName

data class Artist(
    val id: Long,
    val name: String,
    @SerializedName("picture_medium") val picture: String?
)