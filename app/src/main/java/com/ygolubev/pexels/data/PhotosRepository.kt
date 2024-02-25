package com.ygolubev.pexels.data

import com.ygolubev.pexels.data.api.PexelsApi
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapper

internal interface PhotosRepository {

    suspend fun getCuratedPhotos(
        page: Int,
        pageSize: Int
    ): Page<List<Photo>>

}

internal class PhotosRepositoryImpl(
    private val pexelsApi: PexelsApi,
    private val photoJsonToPhotoMapper: PhotoJsonToPhotoMapper,
) : PhotosRepository {

    override suspend fun getCuratedPhotos(
        page: Int,
        pageSize: Int,
    ): Page<List<Photo>> =
        pexelsApi.getCuratedPhotos(page, pageSize).let {
            Page(
                hasNext = it.nextPage != null,
                data = it.photos.map(photoJsonToPhotoMapper::map)
            )
        }

}
