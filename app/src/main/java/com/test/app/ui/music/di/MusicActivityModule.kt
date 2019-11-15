package com.test.app.ui.music.di

import com.test.app.domain.api.DeezerApi
import com.test.app.domain.repository.IMusicRepository
import com.test.app.domain.repository.MusicRepository
import com.test.app.net.rx.ISchedulerFactory
import com.test.app.ui.music.adapters.AlbumAdapter
import com.test.app.ui.music.MusicViewModel
import com.test.app.ui.music.adapters.AlbumDetailAdapter
import com.test.app.ui.music.adapters.SearchAdapter
import dagger.Module
import dagger.Provides

@Module
class MusicActivityModule {

    @Provides
    fun providesMusicRepository(
        movieApi: DeezerApi
    ): IMusicRepository = MusicRepository(movieApi)

    @Provides
    fun providesMusicViewModel(
        repository: IMusicRepository,
        schedulerFactory: ISchedulerFactory
    ): MusicViewModel = MusicViewModel(repository, schedulerFactory)

    @Provides
    fun providesAlbumAdapter() = AlbumAdapter()

    @Provides
    fun providesSearchAdapter() = SearchAdapter()

    @Provides
    fun providesAlbumDetailAdapter() = AlbumDetailAdapter()
}