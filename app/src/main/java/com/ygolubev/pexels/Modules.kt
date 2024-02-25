package com.ygolubev.pexels

import android.util.Log
import androidx.paging.PagingSource
import com.ygolubev.pexels.data.Photo
import com.ygolubev.pexels.data.PhotosPagingSource
import com.ygolubev.pexels.data.PhotosRepository
import com.ygolubev.pexels.data.PhotosRepositoryImpl
import com.ygolubev.pexels.data.api.PexelsApi
import com.ygolubev.pexels.data.api.PexelsApiImpl
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapper
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapperImpl
import com.ygolubev.pexels.ui.model.AppViewModel
import com.ygolubev.pexels.ui.model.CuratedPhotosViewModel
import com.ygolubev.pexels.ui.model.PhotoDetailsViewModel
import com.ygolubev.pexels.ui.model.PhotoToCuratedPhotoUiModelMapper
import com.ygolubev.pexels.ui.model.PhotoToCuratedPhotoUiModelMapperImpl
import com.ygolubev.pexels.ui.navigation.Navigator
import com.ygolubev.pexels.ui.navigation.NavigatorImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
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
            install(HttpCache) {
                publicStorage(
                    FileStorage(
                        androidContext()
                            .cacheDir
                            .resolve("http_cache")
                    )
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Http", message)
                    }
                }
                level = LogLevel.ALL
                filter { true }
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
    singleOf(::PhotosPagingSource) {
        bind<PagingSource<Int, Photo>>()
    }
}

internal val uiModule = module {
    singleOf(::NavigatorImpl) {
        bind<Navigator>()
    }

    viewModelOf(::AppViewModel)

    singleOf(::PhotoToCuratedPhotoUiModelMapperImpl) {
        bind<PhotoToCuratedPhotoUiModelMapper>()
    }
    viewModelOf(::CuratedPhotosViewModel)

    viewModelOf(::PhotoDetailsViewModel)
}
