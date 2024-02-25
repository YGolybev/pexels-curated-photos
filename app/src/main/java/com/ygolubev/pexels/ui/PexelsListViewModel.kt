package com.ygolubev.pexels.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Immutable
internal data class PexelsListUiModel(
    val pexels: List<PexelsImageUiModel> = emptyList()
)

@Immutable
internal data class PexelsImageUiModel(
    val imageModel: Any,
    val author: String,
    val alt: String,
)

internal class PexelsListViewModel : ViewModel() {

    val state = MutableStateFlow(PexelsListUiModel(
        pexels = listOf(
            PexelsImageUiModel(
                imageModel = "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=350",
                author = "Author Name",
                alt = "Content description"
            ),
            PexelsImageUiModel(
                imageModel = "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=350",
                author = "Author Name",
                alt = "Content description"
            ),
            PexelsImageUiModel(
                imageModel = "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=350",
                author = "Author Name",
                alt = "Content description"
            )
        )
    ))

    fun openPexel(pexel: PexelsImageUiModel) {
        // TODO
    }

}
