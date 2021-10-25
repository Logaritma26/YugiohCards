package com.log.yugiohcards.lib.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = BottomRoutes.Home.route,
        modifier = modifier,
    ) {
        composable(BottomRoutes.Home.route) {
            //val viewModel = hiltViewModel<HomePageViewModel>()
            //HomePage(viewModel = viewModel)
        }
        /*composable(
            "card_detail_page/{color}/{card_id}",
            arguments = listOf(
                navArgument("color") {
                    type = NavType.IntType
                    this.defaultValue = Color.Blue
                    this.nullable = false
                },
                navArgument("card_id") {
                    type = NavType.StringType
                    this.nullable = false
                }
            ),
        ) {
            val cardId = remember {
                it.arguments?.getString("cardId")
            }
        }*/
    }

}