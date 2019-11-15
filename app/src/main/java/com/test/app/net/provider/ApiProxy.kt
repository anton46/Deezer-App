package com.test.app.net.provider

import io.reactivex.Observable
import io.reactivex.Scheduler

open class ApiProxy<ApiInterface>(
    private val apiProvider: ApiProvider<ApiInterface>,
    private val computationScheduler: Scheduler
) {

    fun getApiInterface(): ApiInterface {
        return apiProvider.getApi()
    }

    fun <T> retryOnNetworkOrHttpError(observable: Observable<T>): Observable<T> {
        return observable
    }
}