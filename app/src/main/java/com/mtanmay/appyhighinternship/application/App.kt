package com.mtanmay.appyhighinternship.application

import android.annotation.SuppressLint
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}