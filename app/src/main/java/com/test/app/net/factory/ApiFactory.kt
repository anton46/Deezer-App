package com.test.app.net.factory

interface ApiFactory<Api> {
    fun create(): Api
}
