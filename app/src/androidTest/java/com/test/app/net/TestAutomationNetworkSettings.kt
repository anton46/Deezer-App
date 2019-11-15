package com.test.app.net

import com.test.app.net.settings.NetworkSettingsProvider
import com.test.app.net.settings.ServerEnvironment

class TestAutomationNetworkSettings : NetworkSettingsProvider {
    override fun getServerEnvironment(): ServerEnvironment = ServerEnvironment.MOCK
}