package com.test.app.di

import dagger.Module
import dagger.Provides
import com.test.app.net.settings.AppNetworkSettings
import com.test.app.net.settings.NetworkSettingsProvider
import javax.inject.Singleton

@Module
open class NetworkSettingsModule {

    @Provides
    @Singleton
    open fun provideNetworkSettingsProvider(): NetworkSettingsProvider = AppNetworkSettings()

}