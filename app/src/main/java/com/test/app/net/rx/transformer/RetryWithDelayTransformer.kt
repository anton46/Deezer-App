package com.test.app.net.rx.transformer

import rx.Observable
import rx.Scheduler
import com.test.app.net.rx.RetryWithDelay
import com.test.app.net.strategy.DefaultRetryStrategy
import com.test.app.net.strategy.RetryStrategy

class RetryWithDelayTransformer<T> @JvmOverloads constructor(
    private val retryStrategy: RetryStrategy = DefaultRetryStrategy(),
    private val scheduler: Scheduler
) : Observable.Transformer<T, T> {

    override fun call(tObservable: Observable<T>): Observable<T> {
        return tObservable.retryWhen(RetryWithDelay(retryStrategy, scheduler), scheduler)
    }
}
