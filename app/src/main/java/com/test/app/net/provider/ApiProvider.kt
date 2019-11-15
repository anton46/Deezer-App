package com.test.app.net.provider

interface ApiProvider<Api> {
    fun getApi(): Api
}