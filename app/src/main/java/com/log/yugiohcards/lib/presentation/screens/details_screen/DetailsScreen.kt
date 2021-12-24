package com.log.yugiohcards.lib.presentation.screens.details_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.log.yugiohcards.lib.presentation.util.common_composables.ItemCard

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    cardId: Int
    //card: Card
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.getCardWithId(cardId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("asdfasdfasdfa")

            ItemCard(
                card = state.currentCard,
                isLoading = state.isCardLoading,
            )
        }
    }


    /*RandomCard(
        card = card
    )*/
}