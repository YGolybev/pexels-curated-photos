package com.ygolubev.pexels.data.api

import androidx.annotation.IntRange
import com.ygolubev.pexels.data.PhotosRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal interface PexelsApi {

    suspend fun getCuratedPhotos(
        @IntRange(from = 1) page: Int,
    ): CuratedPhotosJson

}

internal class PexelsApiImpl(
    private val httpClient: HttpClient,
) : PexelsApi {

    override suspend fun getCuratedPhotos(
        page: Int,
    ): CuratedPhotosJson =
        httpClient.get("curated") {
            parameter("per_page", PHOTOS_PER_PAGE)
            parameter("page", page)
        }.body<CuratedPhotosJson>()

    companion object {
        private const val PHOTOS_PER_PAGE = 15
    }

}
