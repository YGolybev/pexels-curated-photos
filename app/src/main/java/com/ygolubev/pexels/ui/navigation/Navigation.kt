package com.ygolubev.pexels.ui.navigation

internal sealed class Destination {

    data object CuratedPhotos : Destination()

    data class PhotoDetails(
        val photoId: String,
    ) : Destination()

    data object Back : Destination()

}

internal object NavigationGraph {
    const val curated = "curated"
    fun details(photoId: String? = null): String =
        "details${if (photoId != null) "/${photoId}" else ""}"

    const val ARG_PHOTO_ID = "photoId"

}
