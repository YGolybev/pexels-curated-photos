package com.ygolubev.pexels

import com.ygolubev.pexels.data.PhotosRepository
import com.ygolubev.pexels.data.PhotosRepositoryImpl
import com.ygolubev.pexels.data.api.PexelsApi
import com.ygolubev.pexels.data.api.PexelsApiImpl
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapper
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapperImpl
import com.ygolubev.pexels.ui.screens.CuratedPhotosViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val dataModule = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            defaultRequest {
                url(androidContext().getString(R.string.pexels_url))
                header("Authorization", BuildConfig.PexelsApiKey)
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    singleOf(::PexelsApiImpl) {
        bind<PexelsApi>()
    }
    singleOf(::PhotoJsonToPhotoMapperImpl) {
        bind<PhotoJsonToPhotoMapper>()
    }
    singleOf(::PhotosRepositoryImpl) {
        bind<PhotosRepository>()
    }
}

internal val uiModule = module {
    singleOf(::CuratedPhotosViewModel)
}
