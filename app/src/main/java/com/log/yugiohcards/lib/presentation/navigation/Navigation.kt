package com.log.yugiohcards.lib.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.log.yugiohcards.lib.presentation.screens.details_screen.DetailsScreen
import com.log.yugiohcards.lib.presentation.screens.home_screen.HomePage
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.DETAILS_PAGE
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.HOME
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.SAVED

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = HOME.path(),
        modifier = modifier,
    ) {
        composable(HOME.path()) {
            //val viewModel = hiltViewModel<HomePageViewModel>()
            HomePage(navController)
        }
        composable(
            route = DETAILS_PAGE.path(),
            arguments = DETAILS_PAGE.namedNavArgs(),
        ) {
            val cardId = it.arguments!!.getInt("cardId")
            DetailsScreen(cardId = cardId)
        }
        composable(
            route = SAVED.path(),
        ) {

        }
    }
}