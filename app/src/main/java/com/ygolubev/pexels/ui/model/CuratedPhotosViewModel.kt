package com.ygolubev.pexels.ui.model

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.ygolubev.pexels.data.PhotosPagingSource
import com.ygolubev.pexels.data.PhotosRepository
import com.ygolubev.pexels.ui.navigation.Destination
import com.ygolubev.pexels.ui.navigation.Navigator
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Immutable
internal data class CuratedPhotoUiModel(
    val id: String,
    val imageModel: Any,
    val aspectRatio: Float,
    val author: String,
    val description: String,
)

internal class CuratedPhotosViewModel(
    private val photosRepository: PhotosRepository,
    private val photoToCuratedPhotoUiModelMapper: PhotoToCuratedPhotoUiModelMapper,
    private val navigator: Navigator,
) : ViewModel() {

    val photos = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { PhotosPagingSource(photosRepository) },
    ).flow
        .cachedIn(viewModelScope)
        .map { it.map(photoToCuratedPhotoUiModelMapper::map) }

    fun openPhoto(photo: CuratedPhotoUiModel) {
        viewModelScope.launch {
            navigator.navigate(Destination.PhotoDetails(photo.id))
        }
    }

}
