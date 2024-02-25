package com.ygolubev.pexels.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ygolubev.pexels.data.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Immutable
internal data class CuratedPhotosUiModel(
    val photos: List<CuratedPhotoUiModel> = emptyList()
)

@Immutable
internal data class CuratedPhotoUiModel(
    val imageModel: Any,
    val author: String,
    val alt: String,
)

internal class CuratedPhotosViewModel(
    private val photosRepository: PhotosRepository,
) : ViewModel() {

    val state = MutableStateFlow(CuratedPhotosUiModel())

    fun refresh() {
        viewModelScope.launch {
            val photos = photosRepository.getCuratedPhotos(1)
            state.value = CuratedPhotosUiModel(
                photos = photos.map {
                    CuratedPhotoUiModel(
                        imageModel = it.imageUrl,
                        author = it.authorName,
                        alt = it.description,
                    )
                }
            )
        }
    }

    fun openPhoto(photo: CuratedPhotoUiModel) {
        // TODO
    }

}
