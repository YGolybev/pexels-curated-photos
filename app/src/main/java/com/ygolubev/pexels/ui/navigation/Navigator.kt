package com.ygolubev.pexels.ui.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal interface Navigator {

    val destination: Flow<Destination>

    suspend fun navigate(destination: Destination)

}

internal class NavigatorImpl : Navigator {

    override val destination = MutableSharedFlow<Destination>()

    override suspend fun navigate(destination: Destination) {
        this.destination.emit(destination)
    }

}
