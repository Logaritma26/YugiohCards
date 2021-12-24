package com.log.yugiohcards.lib.presentation.navigation

import androidx.navigation.NavType

/*enum class EnumRoutes(val route: Route) {
    HOME(route = Route(baseRoute = "home")),
    SAVED(route = Route(baseRoute = "saved")),
    SEARCH(route = Route(baseRoute = "search")),
    DETAILS_PAGE(
        route = Route(
            baseRoute = "details",
            args = mapOf("cardId" to ArgSpecs(type = NavType.IntType))
        )
    );
}

sealed class SealedRoute(val route: Route) {
    object HOME : SealedRoute(Route(baseRoute = "home"))
    object SAVED : SealedRoute(Route(baseRoute = "saved"))
    object SEARCH : SealedRoute(Route(baseRoute = "search"))
    object DETAILS_PAGE : SealedRoute(
        Route(
            baseRoute = "details",
            args = mapOf("cardId" to ArgSpecs(type = NavType.IntType))
        )
    )
}*/

object ObjectRoutes {

    // Bottom Routes
    val HOME = Route(baseRoute = "home")
    val SAVED = Route(baseRoute = "saved")
    val SEARCH = Route(baseRoute = "search")

    //
    val DETAILS_PAGE = Route(
        baseRoute = "details",
        args = mapOf("cardId" to ArgSpecs(type = NavType.IntType))
    )
}