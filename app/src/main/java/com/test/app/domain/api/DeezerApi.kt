package com.test.app.domain.api

import com.test.app.net.data.response.GetAlbumResponse
import com.test.app.net.data.response.GetTracksResponse
import com.test.app.net.data.response.SearchArtistsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApi {

    @GET("search/artist")
    fun searchArtists(@Query("q") query: String): Observable<SearchArtistsResponse>

    @GET("/artist/{artist_id}/albums")
    fun getAlbum(@Path("artist_id") artistId: String): Single<GetAlbumResponse>

    @GET("/album/{album_id}/tracks")
    fun getTracks(@Path("album_id") albumId: String): Single<GetTracksResponse>
}
