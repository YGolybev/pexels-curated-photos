package com.ygolubev.pexels.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut


internal fun <T> scaleEnterTransition(): AnimatedContentTransitionScope<T>.() -> EnterTransition = {
    scaleIn(
        initialScale = TRANSITION_TARGET_SCALE,
        animationSpec = screenTransitionSpec()
    )
}

internal fun <T> scaleExitTransition(): AnimatedContentTransitionScope<T>.() -> ExitTransition = {
    scaleOut(
        targetScale = TRANSITION_TARGET_SCALE,
        animationSpec = screenTransitionSpec()
    )
}

internal fun <T> slideInEnterTransition(): AnimatedContentTransitionScope<T>.() -> EnterTransition =
    {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Up,
            animationSpec = screenTransitionSpec()
        )
    }

internal fun <T> slideOutExitTransition(): AnimatedContentTransitionScope<T>.() -> ExitTransition =
    {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Down,
            animationSpec = screenTransitionSpec()
        )
    }

internal const val TRANSITION_TARGET_SCALE = 0.9f

internal fun <T> screenTransitionSpec() =
    tween<T>(375)
