package com.ygolubev.pexels

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PexelsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PexelsApplication)
            modules(uiModule, dataModule)
        }
    }

}
