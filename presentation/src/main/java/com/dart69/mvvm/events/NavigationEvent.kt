package com.dart69.mvvm.events

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import com.dart69.mvvm.strings.StringResource

interface NavigationEvent : Event {
    fun applyOn(navController: NavController)
}

data class NavigateToRoute(
    private val routeUri: StringResource,
) : NavigationEvent {
    override fun applyOn(navController: NavController) =
        navController.navigate(
            NavDeepLinkRequest.Builder.fromUri(
                routeUri.asString(navController.context).toUri(),
            ).build()
        )
}

data class NavigateToDirections(
    private val directions: NavDirections,
) : NavigationEvent {
    override fun applyOn(navController: NavController) =
        navController.navigate(directions)
}