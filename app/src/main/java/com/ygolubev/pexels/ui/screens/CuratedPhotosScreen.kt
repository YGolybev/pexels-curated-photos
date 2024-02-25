@file:OptIn(ExperimentalMaterial3Api::class)

package com.ygolubev.pexels.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.ygolubev.pexels.ui.components.PhotoCard
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
internal fun CuratedPhotosScreen(
    model: CuratedPhotosViewModel = koinViewModel()
) {
    val state by model.state.collectAsState()

    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        model.refresh()
    }

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(Unit) {
            // TODO fetch something, also use startRefresh as loading indicator
            delay(1500)
            pullToRefreshState.endRefresh()
        }
    }

    Box(
        modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {
        LazyColumn(content = {
            items(state.photos) { photo ->
                PhotoCard(
                    model = photo,
                    onClick = model::openPhoto,
                )
            }
        })

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
        )
    }

}
