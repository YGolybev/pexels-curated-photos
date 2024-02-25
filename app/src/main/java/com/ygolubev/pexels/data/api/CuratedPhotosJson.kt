package com.ygolubev.pexels.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CuratedPhotosJson(
    @SerialName("photos")
    val photos: List<PhotoJson>
)
