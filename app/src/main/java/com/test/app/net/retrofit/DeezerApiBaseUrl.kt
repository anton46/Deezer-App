package com.test.app.net.retrofit

import com.test.app.net.settings.NetworkSettingsProvider
import com.test.app.net.settings.ServerEnvironment

class DeezerApiBaseUrl(networkSettingsProvider: NetworkSettingsProvider) : AbstractBaseUrl(networkSettingsProvider) {

    override fun getHostForEnvironment(environment: ServerEnvironment?): String = when (environment) {
        ServerEnvironment.LIVE -> HOST
        else -> MOCK
    }

    override fun getPathForEnvironment(): String? = null

    companion object {
        const val HOST = "api.deezer.com"
        const val MOCK = "localhost"
    }
}
