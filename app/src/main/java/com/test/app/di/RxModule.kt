package com.test.app.di

import com.test.app.net.rx.ISchedulerFactory
import com.test.app.net.rx.SchedulerFactory
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
open class RxModule {

    companion object {
        const val MAIN = "main"
        const val IO = "io"
        const val COMPUTATION = "computation"
        const val TRAMPOLINE = "trampoline"
    }

    @Provides
    @Singleton
    @Named(MAIN)
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(IO)
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Named(COMPUTATION)
    fun provideComputationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @Singleton
    @Named(TRAMPOLINE)
    fun provideTrampolineScheduler(): Scheduler = Schedulers.trampoline()

    @Provides
    @Singleton
    open fun provideSchedulerFactory(
        @Named(MAIN) mainScheduler: Scheduler,
        @Named(IO) ioScheduler: Scheduler,
        @Named(COMPUTATION) computationScheduler: Scheduler,
        @Named(TRAMPOLINE) trampolineScheduler: Scheduler
    ): ISchedulerFactory {
        return SchedulerFactory(
            mainScheduler,
            ioScheduler,
            computationScheduler,
            trampolineScheduler
        )
    }
}