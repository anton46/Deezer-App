package com.test.app.net.provider

import com.test.app.net.factory.ApiFactory

interface ApiProviderFactory {
    fun <Api> create(apiFactory: ApiFactory<Api>): ApiProvider<Api>
}