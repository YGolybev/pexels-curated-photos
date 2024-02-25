package com.ygolubev.pexels.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ygolubev.pexels.ui.model.AppViewModel
import com.ygolubev.pexels.ui.navigation.Destination
import com.ygolubev.pexels.ui.navigation.NavigationGraph.ARG_PHOTO_ID
import com.ygolubev.pexels.ui.navigation.NavigationGraph.curated
import com.ygolubev.pexels.ui.navigation.NavigationGraph.details
import com.ygolubev.pexels.ui.screens.CuratedPhotosScreen
import com.ygolubev.pexels.ui.screens.PhotoDetailsScreen
import com.ygolubev.pexels.ui.theme.PexelsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PexelsApp(
    model: AppViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    LaunchedEffect(Unit) {
        model.destination.collect(navController::handleDestination)
    }

    PexelsTheme {
        NavHost(
            navController = navController,
            startDestination = curated,
        ) {
            composable(curated) {
                CuratedPhotosScreen()
            }

            composable(details("{${ARG_PHOTO_ID}}")) {
                PhotoDetailsScreen()
            }
        }
    }
}

internal fun NavHostController.handleDestination(destination: Destination) {
    when (destination) {
        Destination.CuratedPhotos ->
            navigate(curated)

        is Destination.PhotoDetails ->
            navigate(details(destination.photoId))

        Destination.Back ->
            popBackStack()
    }
}
