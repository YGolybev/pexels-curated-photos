package com.ygolubev.pexels.data

data class Page<T>(
    val hasNext: Boolean,
    val data: T,
)
