package com.ygolubev.pexels.data.api

import com.ygolubev.pexels.data.Photo

internal interface PhotoJsonToPhotoMapper {

    fun map(json: PhotoJson): Photo

}

internal class PhotoJsonToPhotoMapperImpl : PhotoJsonToPhotoMapper {

    override fun map(json: PhotoJson): Photo =
        Photo(
            id = json.id.toString(),
            thumbnailModel = json.src.large,
            originalModel = json.src.original,
            authorName = json.photographer,
            description = json.description,
            aspectRatio = json.width.toFloat() / json.height,
        )

}
