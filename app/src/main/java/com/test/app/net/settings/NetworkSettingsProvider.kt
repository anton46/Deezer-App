package com.test.app.net.settings

interface NetworkSettingsProvider {
    fun getServerEnvironment(): ServerEnvironment
}