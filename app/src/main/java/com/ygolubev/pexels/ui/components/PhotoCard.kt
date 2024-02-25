package com.ygolubev.pexels.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(model.imageModel)
            .scale(Scale.FIT)
            .build()
    )

    ElevatedCard(
        onClick = { onClick(model) },
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.FillWidth,
            contentDescription = model.description,
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (painter.state !is AsyncImagePainter.State.Success)
                        Modifier.aspectRatio(model.aspectRatio)
                    else Modifier
                )
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Text(
            text = model.author,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}
