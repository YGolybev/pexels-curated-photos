package com.ygolubev.pexels.data

internal data class Photo(
    val imageUrl: String,
    val aspectRatio: Float,
    val authorName: String,
    val description: String,
)
