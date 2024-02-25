package com.ygolubev.pexels.ui.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ygolubev.pexels.data.PhotosRepository
import com.ygolubev.pexels.ui.navigation.Destination
import com.ygolubev.pexels.ui.navigation.NavigationGraph.ARG_PHOTO_ID
import com.ygolubev.pexels.ui.navigation.Navigator
import kotlinx.coroutines.launch

internal class PhotoDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    photosRepository: PhotosRepository,
    private val navigator: Navigator,
) : ViewModel() {

    private val photoId: String = checkNotNull(savedStateHandle[ARG_PHOTO_ID])

    val photo = photosRepository.getPhoto(photoId)

    fun goBack() {
        viewModelScope.launch {
            navigator.navigate(Destination.Back)
        }
    }

}
