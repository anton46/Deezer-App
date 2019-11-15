package com.test.app.net.factory

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import com.test.app.net.retrofit.IBaseUrlProviderFactory

class GenericApiFactory<Api>(
    private val client: OkHttpClient,
    private val urlProviderFactory: IBaseUrlProviderFactory,
    private val converterFactory: Converter.Factory?,
    private val interfaceClass: Class<Api>
) : ApiFactory<Api> {

    override fun create(): Api {
        val retrofit = createRetrofit()
        return retrofit.create<Api>(interfaceClass)
    }

    private fun createRetrofit(): Retrofit {
        val baseUrlProvider = urlProviderFactory.create()

        val builder = Retrofit.Builder()
        if (converterFactory != null) {
            builder.addConverterFactory(converterFactory)
        }

        return builder
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .baseUrl(baseUrlProvider.url())
            .build()
    }
}
