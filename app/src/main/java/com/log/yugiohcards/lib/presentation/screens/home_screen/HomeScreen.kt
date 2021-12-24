package com.log.yugiohcards.lib.presentation.screens.home_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.log.yugiohcards.lib.presentation.util.common_composables.ItemCard
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.DETAILS_PAGE
import timber.log.Timber

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(true) {
        viewModel.getCard()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(onClick = { viewModel.getCard() }) {
                Text(text = "hello get cards")
            }
            Spacer(modifier = Modifier.height(16.dp))
            ItemCard(
                card = state.currentCard,
                isLoading = state.isCardLoading,
                fraction = 0.77f,
                onClick = {
                    state.currentCard?.let { it ->
                        val args = mapOf("cardId" to it.id)
                        DETAILS_PAGE.provideNavPath(args)?.let { path ->
                            Timber.d("path: $path")
                            navController.navigate(path)
                        }
                    }
                }
            )
        }
    }
}