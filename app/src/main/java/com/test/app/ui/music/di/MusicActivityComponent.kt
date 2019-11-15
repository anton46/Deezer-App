package com.test.app.ui.music.di

import com.test.app.ui.di.ActivityComponent
import com.test.app.ui.music.AlbumActivity
import com.test.app.ui.music.AlbumDetailActivity
import com.test.app.ui.music.SearchArtistActivity
import dagger.Subcomponent

@Subcomponent(modules = [MusicActivityModule::class])
interface MusicActivityComponent : ActivityComponent {
    fun inject(homeActivity: SearchArtistActivity)

    fun inject(albumActivity: AlbumActivity)

    fun inject(albumDetailActivity: AlbumDetailActivity)
}