package com.test.app.domain.repository

import com.test.app.net.data.response.Album
import com.test.app.net.data.response.Artist
import com.test.app.net.data.response.Track
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Path

interface IMusicRepository {
    fun searchArtists(query: String): Observable<List<Artist>>

    fun getAlbum(artistId: String): Single<List<Album>>

    fun getTracks(albumId: String): Single<List<Track>>
}
