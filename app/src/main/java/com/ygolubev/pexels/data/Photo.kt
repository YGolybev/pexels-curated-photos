package com.ygolubev.pexels.data

internal data class Photo(
    val id: String,
    val thumbnailModel: String,
    val originalModel: String,
    val aspectRatio: Float,
    val authorName: String,
    val description: String,
)
