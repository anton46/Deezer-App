package com.test.app.ui.music

import com.test.app.domain.repository.IMusicRepository
import com.test.app.net.data.response.Album
import com.test.app.net.data.response.Artist
import com.test.app.net.data.response.Track
import com.test.app.net.rx.ISchedulerFactory
import com.test.app.ui.music.model.AlbumViewModel
import com.test.app.ui.music.model.ArtistViewModel
import com.test.app.ui.music.model.SongViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MusicViewModelTest {

    private val testScheduler = TestScheduler()

    @Mock
    private lateinit var musicRepository: IMusicRepository

    @Mock
    private lateinit var schedulerFactory: ISchedulerFactory

    private lateinit var musicViewModel: MusicViewModel

    @Before
    fun setUp() {
        `when`(schedulerFactory.io()).thenReturn(testScheduler)
        `when`(schedulerFactory.main()).thenReturn(testScheduler)

        musicViewModel = MusicViewModel(musicRepository, schedulerFactory)
    }

    @Test
    fun testSearchArtists() {
        val query = "prince"
        val expectedData = listOf(Artist(1, "Prince", "picture"))

        `when`(musicRepository.searchArtists(query)).thenReturn(Observable.just(expectedData))

        val states = musicViewModel.observeSates().test()
        musicViewModel.searchArtists(query)

        states.assertValue(MusicViewModel.ViewState.Loading)

        testScheduler.triggerActions()
        verify(musicRepository).searchArtists(query)

        states.assertValueAt(1, MusicViewModel.ViewState.ArtistsLoaded(expectedData.map { it.toViewModel() }))
        states.assertNoErrors()
    }

    @Test
    fun testGetAlbum() {
        val artistId = "1"
        val expectedData = listOf(Album(1, "Album 1", "Cover"))

        `when`(musicRepository.getAlbum(artistId)).thenReturn(Single.just(expectedData))

        val states = musicViewModel.observeSates().test()
        musicViewModel.getAlbums(artistId)

        states.assertValue(MusicViewModel.ViewState.Loading)

        testScheduler.triggerActions()
        verify(musicRepository).getAlbum(artistId)

        states.assertValueAt(1, MusicViewModel.ViewState.AlbumsLoaded(expectedData.map { it.toViewModel() }))
        states.assertNoErrors()
    }

    @Test
    fun testGetTracks() {
        val albumId = "1"
        val expectedData = listOf(Track("Album 1", Artist(1, "Prince", "picture")))

        `when`(musicRepository.getTracks(albumId)).thenReturn(Single.just(expectedData))

        val states = musicViewModel.observeSates().test()
        musicViewModel.getTracks(albumId)

        states.assertValue(MusicViewModel.ViewState.Loading)

        testScheduler.triggerActions()
        verify(musicRepository).getTracks(albumId)

        states.assertValueAt(1, MusicViewModel.ViewState.TracksLoaded(expectedData.map { it.toViewModel() }))
        states.assertNoErrors()
    }

    private fun Artist.toViewModel() = ArtistViewModel(this.id, this.name, this.picture)

    private fun Album.toViewModel() = AlbumViewModel(this.id, this.title, this.cover)

    private fun Track.toViewModel() = SongViewModel(this.title, this.artist.toViewModel())

}