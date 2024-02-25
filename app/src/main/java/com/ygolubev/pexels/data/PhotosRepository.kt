package com.ygolubev.pexels.data

import com.ygolubev.pexels.data.api.CuratedPhotosJson
import com.ygolubev.pexels.data.api.PexelsApi
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal interface PhotosRepository {

    suspend fun getCuratedPhotos(page: Int): List<Photo>

}

internal class PhotosRepositoryImpl(
    private val pexelsApi: PexelsApi,
    private val photoJsonToPhotoMapper: PhotoJsonToPhotoMapper,
) : PhotosRepository {

    override suspend fun getCuratedPhotos(page: Int): List<Photo> =
        pexelsApi.getCuratedPhotos(page)
            .photos
            .map(photoJsonToPhotoMapper::map)

}
