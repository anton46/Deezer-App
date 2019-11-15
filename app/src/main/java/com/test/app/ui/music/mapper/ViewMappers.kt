package com.test.app.ui.music.mapper

import com.test.app.net.data.response.Album
import com.test.app.net.data.response.Artist
import com.test.app.net.data.response.Track
import com.test.app.ui.music.model.AlbumViewModel
import com.test.app.ui.music.model.ArtistViewModel
import com.test.app.ui.music.model.SongViewModel

fun Artist.toViewModel() = ArtistViewModel(this.id, this.name, this.picture)

fun Album.toViewModel() = AlbumViewModel(this.id, this.title, this.cover)

fun Track.toViewModel() = SongViewModel(this.title, this.artist.toViewModel())