package com.test.app.ui.music

import com.test.app.domain.repository.IMusicRepository
import com.test.app.net.rx.ISchedulerFactory
import com.test.app.ui.music.mapper.toViewModel
import com.test.app.ui.music.model.AlbumViewModel
import com.test.app.ui.music.model.ArtistViewModel
import com.test.app.ui.music.model.SongViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class MusicViewModel(
    private val repository: IMusicRepository,
    private val schedulerFactory: ISchedulerFactory
) {
    private val compositeDisposable = CompositeDisposable()

    private val events = PublishSubject.create<Event>()
    private val states = PublishSubject.create<ViewState>()

    init {
        compositeDisposable.add(
            events.subscribe {
                when (it) {
                    is Event.Search -> searchArtistsFromApi(it.query)
                    is Event.GetAlbum -> getAlbumFromApi(it.artistId)
                    is Event.GetTrack -> getTracksFromApi(it.albumId)
                }
            }
        )
    }

    fun observeSates(): Observable<ViewState> = states.hide()

    private fun searchArtistsFromApi(query: String) {
        states.onNext(ViewState.Loading)
        compositeDisposable.add(
            createSearchArtistObservable(query)
                .switchIfEmpty(Observable.just(listOf()))
                .observeOn(schedulerFactory.main())
                .subscribeOn(schedulerFactory.io())
                .subscribe(::onSearchResult) {
                    it.printStackTrace()
                    onError(it.message)
                }
        )
    }

    private fun createSearchArtistObservable(query: String) =
        if (query.isNotEmpty()) {
            Observable
                .just(query)
                .switchMap { repository.searchArtists(query) }
                .map { artists -> artists.map { it.toViewModel() } }
        } else Observable.empty()

    private fun getAlbumFromApi(artistId: String) {
        states.onNext(ViewState.Loading)
        compositeDisposable.add(
            repository.getAlbum(artistId)
                .map { artists -> artists.map { it.toViewModel() } }
                .observeOn(schedulerFactory.main())
                .subscribeOn(schedulerFactory.io())
                .subscribe(::onAlbumLoaded) { onError(it.message) }
        )
    }

    private fun getTracksFromApi(albumId: String) {
        states.onNext(ViewState.Loading)
        compositeDisposable.add(
            repository.getTracks(albumId)
                .map { tracks -> tracks.map { it.toViewModel() } }
                .observeOn(schedulerFactory.main())
                .subscribeOn(schedulerFactory.io())
                .subscribe(::onTracksLoaded) { onError(it.message) }
        )
    }

    private fun onSearchResult(artists: List<ArtistViewModel>) {
        states.onNext(ViewState.ArtistsLoaded(artists))
    }

    private fun onAlbumLoaded(albums: List<AlbumViewModel>) {
        states.onNext(ViewState.AlbumsLoaded(albums))
    }

    private fun onTracksLoaded(tracks: List<SongViewModel>) {
        states.onNext(ViewState.TracksLoaded(tracks))
    }

    private fun onError(message: String?) {
        println("ERRRRORRR>>> " + message)
        states.onNext(ViewState.Error(message))
    }

    fun searchArtists(query: String) {
        events.onNext(Event.Search(query))
    }

    fun getAlbums(artistId: String) {
        events.onNext(Event.GetAlbum(artistId))
    }

    fun getTracks(albumsId: String) {
        events.onNext(Event.GetTrack(albumsId))
    }

    fun dispose() {
        compositeDisposable.clear()
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class AlbumsLoaded(val albums: List<AlbumViewModel>) : ViewState()
        data class ArtistsLoaded(val artists: List<ArtistViewModel>) : ViewState()
        data class TracksLoaded(val tracks: List<SongViewModel>) : ViewState()
        data class Error(val message: String?) : ViewState()
    }

    sealed class Event {
        data class Search(val query: String) : Event()
        data class GetAlbum(val artistId: String) : Event()
        data class GetTrack(val albumId: String) : Event()
    }
}
