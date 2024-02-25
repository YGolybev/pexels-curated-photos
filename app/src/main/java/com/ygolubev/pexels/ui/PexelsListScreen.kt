package com.ygolubev.pexels.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
internal fun PexelsListScreen(
    model: PexelsListViewModel = viewModel()
) {
    val state by model.state.collectAsState()

    LazyColumn(content = {
        items(state.pexels) { pexel ->
            PexelsImage(
                model = pexel,
                onClick = model::openPexel,
            )
        }
    })
}
