package com.log.yugiohcards.lib.presentation.screens.saved_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.log.yugiohcards.lib.presentation.util.common_composables.ShowCardsList

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SavedViewModel = hiltViewModel(),
) {
    val state: SavedState = viewModel.state.value


    LaunchedEffect(key1 = true) {
        viewModel.getFavoriteCards()
    }

    ShowCardsList(
        modifier = modifier.padding(top = 6.dp),
        state = state.cards,
        navController = navController,
    )
}