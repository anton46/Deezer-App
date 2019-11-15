package com.test.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.test.app.di.*

open class MainApplication : Application(), HasApplicationComponent {

    private lateinit var component: MusicApplicationComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        inject()
    }

    open fun inject() {
        component = DaggerMusicApplicationComponent.builder().build()
        component.inject(this)
    }

    override fun getApplicationComponent(): ApplicationComponent = component
}