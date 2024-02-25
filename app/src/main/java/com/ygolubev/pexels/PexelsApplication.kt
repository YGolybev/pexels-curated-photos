package com.ygolubev.pexels

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PexelsApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PexelsApplication)
            modules(uiModule, dataModule)
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .diskCache {
            DiskCache.Builder()
                .directory(cacheDir.resolve("image_cache"))
                .maxSizeBytes(5 * 1024 * 1024)
                .build()
        }
        .build()

}
