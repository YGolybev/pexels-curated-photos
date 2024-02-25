package com.ygolubev.pexels.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.ygolubev.pexels.R
import com.ygolubev.pexels.ui.components.ImageLoadingStateItem
import com.ygolubev.pexels.ui.model.PhotoDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PhotoDetailsScreen(
    model: PhotoDetailsViewModel = koinViewModel(),
) {
    val photo by model.photo.collectAsState(initial = null)

    var retryKey by remember { mutableStateOf(0) }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo?.thumbnailModel)
            .scale(Scale.FIT)
            .setParameter(COIL_RETRY_KEY, retryKey, null)
            .build(),
    )

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
    ) {
        ImageLoadingStateItem(
            state = painter.state,
            onRetryClick = { retryKey++ },
            modifier = Modifier.align(Alignment.Center)
        )

        Image(
            painter = painter,
            contentScale = ContentScale.Fit,
            contentDescription = photo?.description,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
        )

        Text(
            text = stringResource(id = R.string.close),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(all = 16.dp)
                .clickable(
                    enabled = true,
                    onClick = model::goBack,
                )
        )
    }
}

private const val COIL_RETRY_KEY = "retry_key"
