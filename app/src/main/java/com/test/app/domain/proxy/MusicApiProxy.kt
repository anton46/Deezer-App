package com.test.app.domain.proxy

import com.test.app.domain.api.DeezerApi
import com.test.app.net.data.response.GetTracksResponse
import com.test.app.net.provider.ApiProvider
import com.test.app.net.provider.ApiProxy
import io.reactivex.Scheduler
import io.reactivex.Single

class MusicApiProxy(
    apiProvider: ApiProvider<DeezerApi>,
    scheduler: Scheduler
) : ApiProxy<DeezerApi>(apiProvider, scheduler), DeezerApi {

    override fun searchArtists(query: String) = getApiInterface().searchArtists(query)

    override fun getAlbum(artistId: String) = getApiInterface().getAlbum(artistId)

    override fun getTracks(albumId: String) = getApiInterface().getTracks(albumId)
}
