package com.log.yugiohcards.lib.presentation.screens.home_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.DETAILS_PAGE
import com.log.yugiohcards.lib.presentation.util.common_composables.ItemCard

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state: HomeState = viewModel.state.value

    LaunchedEffect(true) {
        viewModel.getCard()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            ItemCard(
                imageUrl = state.currentCard?.card_images?.first()?.image_url,
                isLoading = state.isCardLoading,
                modifier = Modifier.padding(12.dp),
                fraction = 0.77f,
                onClick = {
                    state.currentCard?.let { it ->
                        val args = mapOf("cardId" to it.id, "cardPrice" to 241)
                        DETAILS_PAGE.provideNavPath(args)?.let { path ->
                            navController.navigate(path)
                        }
                    }
                },
                onDoubleClick = {
                    viewModel.getCard()
                }
            )
        }
    }
}