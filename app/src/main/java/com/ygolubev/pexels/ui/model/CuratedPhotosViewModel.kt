package com.ygolubev.pexels.ui.model

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import androidx.paging.map
import com.ygolubev.pexels.data.Photo
import kotlinx.coroutines.flow.map

@Immutable
internal data class CuratedPhotoUiModel(
    val imageModel: Any,
    val author: String,
    val description: String,
)

internal class CuratedPhotosViewModel(
    private val photosPagingSource: PagingSource<Int, Photo>,
    private val photoToCuratedPhotoUiModelMapper: PhotoToCuratedPhotoUiModelMapper,
) : ViewModel() {

    val photos = Pager(
        config = PagingConfig(20),
    ) { photosPagingSource }
        .flow
        .cachedIn(viewModelScope)
        .map { it.map(photoToCuratedPhotoUiModelMapper::map) }

    fun openPhoto(photo: CuratedPhotoUiModel) {
        // TODO
    }

}
