package com.test.app.net.rx

import io.reactivex.Scheduler


interface ISchedulerFactory {
    fun main(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
}