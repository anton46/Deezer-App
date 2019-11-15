package com.test.app.ui.base

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

fun Context.photoSize(grid: Int): Int {
    val metrics = DisplayMetrics()
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels / grid
}
