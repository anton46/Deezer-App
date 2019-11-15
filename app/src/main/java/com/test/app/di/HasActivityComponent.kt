package com.test.app.di

import com.test.app.ui.di.ActivityComponent

interface HasActivityComponent : HasApplicationComponent {
    fun getActivityComponent(): ActivityComponent
}