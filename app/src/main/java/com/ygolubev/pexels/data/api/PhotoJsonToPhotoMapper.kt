package com.ygolubev.pexels.data.api

import com.ygolubev.pexels.data.Photo

internal interface PhotoJsonToPhotoMapper {

    fun map(json: PhotoJson): Photo

}

internal class PhotoJsonToPhotoMapperImpl : PhotoJsonToPhotoMapper {

    override fun map(json: PhotoJson): Photo =
        Photo(
            imageUrl = json.src.medium,
            authorName = json.photographer,
            description = json.alt,
        )

}
