package com.ygolubev.pexels.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PhotoJson(
    @SerialName("photographer")
    val photographer: String,
    @SerialName("src")
    val src: PhotoSrcJson,
    @SerialName("alt")
    val alt: String,
)

@Serializable
internal data class PhotoSrcJson(
    @SerialName("medium")
    val medium: String,
)
