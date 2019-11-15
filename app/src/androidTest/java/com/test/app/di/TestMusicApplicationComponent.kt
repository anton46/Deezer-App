package com.test.app.di

import com.test.app.EspressoApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, RxModule::class, NetworkSettingsModule::class])
interface
TestMusicApplicationComponent : MusicApplicationComponent {
    fun inject(application: EspressoApplication)
}