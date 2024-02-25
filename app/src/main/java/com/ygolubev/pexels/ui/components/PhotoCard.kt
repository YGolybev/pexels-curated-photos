package com.ygolubev.pexels.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.ygolubev.pexels.ui.screens.CuratedPhotoUiModel

@Preview
@Composable
private fun PhotoCardPreview() {
    PhotoCard(
        model = CuratedPhotoUiModel(
            imageModel = "",
            author = "Author Name",
            alt = "Content description"
        ),
        onClick = {}
    )
}

@Composable
internal fun PhotoCard(
    model: CuratedPhotoUiModel,
    onClick: (CuratedPhotoUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = { onClick(model) },
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model.imageModel)
                .size(Size.ORIGINAL)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = model.alt,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Text(
            text = model.author,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}
