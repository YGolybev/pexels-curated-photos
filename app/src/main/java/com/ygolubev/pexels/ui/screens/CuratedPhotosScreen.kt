@file:OptIn(ExperimentalMaterialApi::class)

package com.ygolubev.pexels.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ygolubev.pexels.R
import com.ygolubev.pexels.ui.components.ErrorItem
import com.ygolubev.pexels.ui.components.PhotoCard
import com.ygolubev.pexels.ui.model.CuratedPhotosViewModel
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
internal fun CuratedPhotosScreen(
    model: CuratedPhotosViewModel = koinViewModel()
) {
    val photos = model.photos.collectAsLazyPagingItems()

    val refreshState = photos.loadState.refresh
    val isRefreshing = refreshState is LoadState.Loading
    val isError = refreshState is LoadState.Error

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = photos::refresh,
    )

    val itemCount = photos.itemCount

    // show refreshing error as toast if we have content
    val showErrorToast = isError && itemCount > 0
    if (showErrorToast) {
        val context = LocalContext.current
        val message = stringResource(id = R.string.paging_append_load_failed)
        LaunchedEffect(showErrorToast) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(content = {
            if (refreshState is LoadState.Error && itemCount == 0) {
                // show fullscreen error if there's no content
                item {
                    ErrorItem(
                        onRetryClick = photos::retry,
                        modifier = Modifier.fillParentMaxSize(),
                    )
                }
            } else {
                // content!
                items(photos.itemCount) { index ->
                    val photo = photos[index] ?: return@items
                    PhotoCard(
                        model = photo,
                        onClick = model::openPhoto,
                    )
                }
                appendState(
                    loadState = photos.loadState.append,
                    onRetryClick = photos::retry,
                )
            }
        })

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }

}

internal fun LazyListScope.appendState(
    loadState: LoadState,
    onRetryClick: () -> Unit,
) {
    when (loadState) {
        is LoadState.Loading -> item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }

        is LoadState.Error -> item {
            ErrorItem(
                onRetryClick = onRetryClick,
            )
        }

        is LoadState.NotLoading -> {}
    }
}


