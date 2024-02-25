package com.ygolubev.pexels.data.api

import androidx.annotation.IntRange
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal interface PexelsApi {

    suspend fun getCuratedPhotos(
        @IntRange(from = 1) page: Int,
        @IntRange(from = 1) pageSize: Int,
    ): CuratedPhotosJson

}

internal class PexelsApiImpl(
    private val httpClient: HttpClient,
) : PexelsApi {

    override suspend fun getCuratedPhotos(
        page: Int,
        pageSize: Int,
    ): CuratedPhotosJson = httpClient.get("curated") {
        parameter("per_page", pageSize)
        parameter("page", page)
    }.body()


}
