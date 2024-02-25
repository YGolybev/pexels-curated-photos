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

    val state = MutableStateFlow(PexelsListUiModel())

    fun openPexel(pexel: PexelsImageUiModel) {
        // TODO
    }

}
