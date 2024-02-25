package com.ygolubev.pexels.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.ygolubev.pexels.ui.model.CuratedPhotoUiModel

@Composable
internal fun PhotoCard(
    model: CuratedPhotoUiModel,
    onClick: (CuratedPhotoUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var retryKey by remember { mutableStateOf(0) }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(model.imageModel)
            .setParameter(COIL_RETRY_KEY, retryKey, null)
            .scale(Scale.FIT)
            .build()
    )

    val state = painter.state

    ElevatedCard(
        onClick = {
            if (state is AsyncImagePainter.State.Success) {
                onClick(model)
            }
        },
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            ImageLoadingStateItem(
                state = state,
                onRetryClick = { retryKey++ }
            )

            Image(
                painter = painter,
                contentScale = ContentScale.FillWidth,
                contentDescription = model.description,
                modifier = Modifier
                    .then(
                        if (state !is AsyncImagePainter.State.Success)
                            Modifier.aspectRatio(model.aspectRatio)
                        else Modifier
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }

        Text(
            text = model.author,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}

private const val COIL_RETRY_KEY = "retry_key"
