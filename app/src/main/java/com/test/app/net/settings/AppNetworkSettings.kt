package com.test.app.net.settings

class AppNetworkSettings : NetworkSettingsProvider {
    override fun getServerEnvironment(): ServerEnvironment = ServerEnvironment.LIVE
}