package com.ygolubev.pexels.ui.model

import com.ygolubev.pexels.data.Photo

internal interface PhotoToCuratedPhotoUiModelMapper {

    fun map(photo: Photo): CuratedPhotoUiModel

}

internal class PhotoToCuratedPhotoUiModelMapperImpl : PhotoToCuratedPhotoUiModelMapper {

    override fun map(photo: Photo): CuratedPhotoUiModel =
        CuratedPhotoUiModel(
            id = photo.id,
            imageModel = photo.thumbnailModel,
            author = photo.authorName,
            description = photo.description,
            aspectRatio = photo.aspectRatio,
        )

}
