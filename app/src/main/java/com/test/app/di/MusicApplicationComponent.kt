package com.test.app.di

import com.test.app.MainApplication
import com.test.app.ui.music.di.MusicActivityComponent
import com.test.app.ui.music.di.MusicActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, RxModule::class, NetworkSettingsModule::class])
interface MusicApplicationComponent : ApplicationComponent {
    fun inject(mainApplication: MainApplication)

    fun add(module: MusicActivityModule): MusicActivityComponent
}
