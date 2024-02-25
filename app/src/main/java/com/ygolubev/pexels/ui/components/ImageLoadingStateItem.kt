package com.ygolubev.pexels.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter.State
import com.ygolubev.pexels.R

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
                text = stringResource(id = R.string.photo_load_failed),
                onRetryClick = onRetryClick,
                modifier = modifier,
            )
        }

        else -> Unit
    }
}
