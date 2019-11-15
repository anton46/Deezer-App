package com.test.app.domain.repository

import com.test.app.domain.api.DeezerApi

class MusicRepository(
    private val musicApi: DeezerApi
) : IMusicRepository {

    override fun searchArtists(query: String) =
        musicApi
            .searchArtists(query)
            .map { it.data }

    override fun getAlbum(artistId: String) =
        musicApi
            .getAlbum(artistId)
            .map { it.data }

    override fun getTracks(albumId: String) =
        musicApi.getTracks(albumId)
            .map { it.data }
}