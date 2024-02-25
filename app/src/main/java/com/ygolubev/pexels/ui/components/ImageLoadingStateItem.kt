package com.ygolubev.pexels.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImagePainter.State

@Composable
internal fun ImageLoadingStateItem(
    state: State,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is State.Loading -> {
            CircularProgressIndicator(modifier = modifier)
        }

        is State.Error -> {
            ErrorItem(
                onRetryClick = onRetryClick,
                modifier = modifier,
            )
        }

        else -> Unit
    }
}
