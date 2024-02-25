@file:OptIn(ExperimentalMaterial3Api::class)

package com.ygolubev.pexels.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class PexelsImageUiModel(
    val imageModel: Any,
    val author: String,
    val alt: String,
)

@Preview
@Composable
private fun PexelsImagePreview() {
    PexelsImage(
        model = PexelsImageUiModel(
            imageModel = "https://images.pexels.com/photos/2880507/pexels-photo-2880507.jpeg?auto=compress&cs=tinysrgb&h=350",
            author = "Author Name",
            alt = "Content description"
        ),
        onClick = {}
    )
}

@Composable
fun PexelsImage(
    model: PexelsImageUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = model.imageModel,
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
