package com.test.app.di

import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.test.app.BuildConfig
import com.test.app.domain.api.DeezerApi
import com.test.app.domain.proxy.MusicApiProxy
import com.test.app.net.factory.ApiFactory
import com.test.app.net.factory.GenericApiFactory
import com.test.app.net.provider.ApiProvider
import com.test.app.net.provider.ApiProviderFactory
import com.test.app.net.provider.DefaultApiProvider
import com.test.app.net.retrofit.GsonConverterFactory
import com.test.app.net.retrofit.IBaseUrlProvider
import com.test.app.net.retrofit.IBaseUrlProviderFactory
import com.test.app.net.retrofit.DeezerApiBaseUrl
import com.test.app.net.settings.NetworkSettingsProvider
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder() = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { Log.d(LOG_TAG, it) }

        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        builder: OkHttpClient.Builder,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return builder
            .addNetworkInterceptor(interceptor)
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(false)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideApiProviderFactory(): ApiProviderFactory {
        return object : ApiProviderFactory {
            override fun <Api> create(apiFactory: ApiFactory<Api>): ApiProvider<Api> {
                return DefaultApiProvider(apiFactory.create())
            }
        }
    }

    @Singleton
    @Provides
    fun provideWeatherAPI(
        apiFactory: ApiFactory<DeezerApi>,
        apiProviderFactory: ApiProviderFactory,
        @Named(RxModule.COMPUTATION) schedulerFactory: Scheduler
    ): DeezerApi {
        return MusicApiProxy(apiProviderFactory.create(apiFactory), schedulerFactory)
    }

    @Singleton
    @Provides
    @Named(WEATHER)
    fun provideWeatherApiBaseUrl(networkSettingsProvider: NetworkSettingsProvider): IBaseUrlProviderFactory {
        return object : IBaseUrlProviderFactory {
            override fun create(): IBaseUrlProvider {
                return DeezerApiBaseUrl(networkSettingsProvider)
            }
        }
    }

    @Singleton
    @Provides
    fun provideWeatherApiFactory(
        client: OkHttpClient,
        @Named(WEATHER) urlProviderFactory: IBaseUrlProviderFactory,
        converterFactory: GsonConverterFactory
    ): ApiFactory<DeezerApi> {
        return GenericApiFactory(
            client,
            urlProviderFactory,
            converterFactory,
            DeezerApi::class.java
        )
    }

    companion object {
        private const val LOG_TAG = "HTTP"
        private const val WEATHER = "WEATHER"
    }
}