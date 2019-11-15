package com.test.app.domain.repository

import com.test.app.domain.api.DeezerApi
import com.test.app.net.data.response.*
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MusicRepositoryTest {

    @Mock
    lateinit var deezerApi: DeezerApi

    lateinit var repository: MusicRepository

    @Before
    fun setUp() {
        repository = MusicRepository(deezerApi)
    }

    @Test
    fun testSearchArtist() {
        val query = "Prince"
        val expectedData = listOf(Artist(1, "Prince", "picture"))

        `when`(deezerApi.searchArtists(query)).thenReturn(Observable.just(SearchArtistsResponse(expectedData)))

        val subscriber = repository.searchArtists(query).test()

        verify(deezerApi).searchArtists(query)

        subscriber.assertValue(expectedData)
        subscriber.assertComplete()
    }

    @Test
    fun testGetAlbum() {
        val artistId = "1"
        val expectedData = listOf(Album(1, "Album 1", "Cover"))

        `when`(deezerApi.getAlbum(artistId)).thenReturn(Single.just(GetAlbumResponse(expectedData)))

        val subscriber = repository.getAlbum(artistId).test()

        verify(deezerApi).getAlbum(artistId)

        subscriber.assertValue(expectedData)
        subscriber.assertComplete()
    }

    @Test
    fun testGetTracks() {
        val albumId = "1"
        val expectedData = listOf(Track("Album 1", Artist(1, "Prince", "picture")))

        `when`(deezerApi.getTracks(albumId)).thenReturn(Single.just(GetTracksResponse(expectedData)))

        val subscriber = repository.getTracks(albumId).test()

        verify(deezerApi).getTracks(albumId)

        subscriber.assertValue(expectedData)
        subscriber.assertComplete()
    }
}