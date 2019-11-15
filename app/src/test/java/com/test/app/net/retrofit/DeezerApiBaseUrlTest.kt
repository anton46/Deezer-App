package com.test.app.net.retrofit

import com.test.app.extentions.shouldEqual
import com.test.app.net.settings.NetworkSettingsProvider
import com.test.app.net.settings.ServerEnvironment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeezerApiBaseUrlTest {

    @Mock
    lateinit var networkSettingsProvider: NetworkSettingsProvider

    lateinit var apiUrl: AbstractBaseUrl

    @Before
    fun test() {
        apiUrl = DeezerApiBaseUrl(networkSettingsProvider)
    }

    @Test
    fun testGetHostLiveEnvironment() {
        apiUrl.getHostForEnvironment(ServerEnvironment.LIVE) shouldEqual "api.deezer.com"
    }

    @Test
    fun testGetHostMockEnvironment() {
        `when`(networkSettingsProvider.getServerEnvironment()).thenReturn(ServerEnvironment.MOCK)

        apiUrl = FakeMusicBaseUrl(networkSettingsProvider)
        apiUrl.getHostForEnvironment(ServerEnvironment.MOCK) shouldEqual "localhost"
        apiUrl.url().port() shouldEqual 8080
        apiUrl.url().encodedPath() shouldEqual "/pathEnvironment/"
    }

   private class FakeMusicBaseUrl(networkSettingsProvider: NetworkSettingsProvider) :
        AbstractBaseUrl(networkSettingsProvider) {

        override fun getHostForEnvironment(environment: ServerEnvironment?): String = when (environment) {
            ServerEnvironment.LIVE -> HOST
            else -> MOCK
        }

        override fun getPathForEnvironment(): String = "/pathEnvironment/"

        companion object {
            const val HOST = "api.deezer.com"
            const val MOCK = "localhost"
        }
    }
}
