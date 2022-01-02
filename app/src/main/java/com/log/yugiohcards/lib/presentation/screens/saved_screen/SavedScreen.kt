package com.log.yugiohcards.lib.presentation.screens.saved_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes
import com.log.yugiohcards.lib.presentation.util.common_composables.card_list_tile.CardListTile
import timber.log.Timber

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

    val listState = rememberLazyListState()
    LaunchedEffect(key1 = true) {
        viewModel.getFavoriteCards()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.cards) { card ->
                CardListTile(
                    card = card,
                    cardOnClick = {
                        val args = mapOf("cardId" to card.id)
                        ObjectRoutes.DETAILS_PAGE.provideNavPath(args)?.let { path ->
                            Timber.d("path: $path")
                            navController.navigate(path)
                        }
                    }
                )
            }
        }
    }
}