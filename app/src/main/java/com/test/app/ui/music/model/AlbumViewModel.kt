package com.test.app.ui.music.model

import java.io.Serializable

data class AlbumViewModel(
    val id: Int,
    val title: String,
    val cover: String,
    val artist: String? = null
) : Serializable
