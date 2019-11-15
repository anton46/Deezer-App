package com.test.app

import com.test.app.di.ApplicationComponent
import com.test.app.di.DaggerTestMusicApplicationComponent
import com.test.app.di.TestMusicApplicationComponent
import com.test.app.net.TestNetworkSettingsModule
import com.test.app.net.TestRxModule

class EspressoApplication : MainApplication() {
    private lateinit var component: TestMusicApplicationComponent

    override fun inject() {
        component = DaggerTestMusicApplicationComponent.builder()
            .networkSettingsModule(TestNetworkSettingsModule())
            .rxModule(TestRxModule())
            .build()
        component.inject(this)
    }

    override fun getApplicationComponent(): ApplicationComponent = component
}