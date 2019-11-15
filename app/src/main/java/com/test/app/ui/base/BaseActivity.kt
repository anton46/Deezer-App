package com.test.app.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.app.di.HasActivityComponent

abstract class BaseActivity : AppCompatActivity(), HasActivityComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    abstract fun inject()
}