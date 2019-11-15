package com.test.app.net

import com.test.app.di.NetworkSettingsModule
import com.test.app.net.settings.NetworkSettingsProvider

class TestNetworkSettingsModule : NetworkSettingsModule() {

    override fun provideNetworkSettingsProvider(): NetworkSettingsProvider = TestAutomationNetworkSettings()
}