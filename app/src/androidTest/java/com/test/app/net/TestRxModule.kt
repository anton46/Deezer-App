package com.test.app.net

import com.test.app.di.RxModule
import com.test.app.net.rx.ISchedulerFactory
import com.test.app.net.rx.SchedulerFactory
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class TestRxModule : RxModule() {

    override fun provideSchedulerFactory(
        mainScheduler: Scheduler,
        ioScheduler: Scheduler,
        computationScheduler: Scheduler,
        trampolineScheduler: Scheduler
    ): ISchedulerFactory {
        return SchedulerFactory(
            AndroidSchedulers.mainThread(),
            AndroidSchedulers.mainThread(),
            AndroidSchedulers.mainThread(),
            AndroidSchedulers.mainThread()
        )
    }
}