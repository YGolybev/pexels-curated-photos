package com.ygolubev.pexels.data

import com.ygolubev.pexels.data.api.PexelsApi
import com.ygolubev.pexels.data.api.PhotoJsonToPhotoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update

internal interface PhotosRepository {

    suspend fun getCuratedPhotos(
        pageIndex: Int,
        pageSize: Int,
    ): Page<List<Photo>>

    fun getPhoto(
        id: String,
    ): Flow<Photo>

}

internal class PhotosRepositoryImpl(
    private val pexelsApi: PexelsApi,
    private val photoJsonToPhotoMapper: PhotoJsonToPhotoMapper,
) : PhotosRepository {

    private val photos = MutableStateFlow<List<Photo>>(emptyList())

    override suspend fun getCuratedPhotos(
        pageIndex: Int,
        pageSize: Int,
    ): Page<List<Photo>> =
        pexelsApi.getCuratedPhotos(pageIndex, pageSize).let {
            Page(
                hasNext = it.nextPage != null,
                data = it.photos.map(photoJsonToPhotoMapper::map)
            )
        }.also { page ->
            photos.update { it + page.data }
        }

    override fun getPhoto(id: String): Flow<Photo> =
        photos.mapNotNull { photos ->
            photos.find { it.id == id }
        }

}
