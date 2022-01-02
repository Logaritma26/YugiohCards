package com.log.yugiohcards.lib.presentation.screens.details_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.log.yugiohcards.lib.presentation.util.common_composables.ItemCard

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    cardId: Int,
) {
    val state: DetailState = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.getCardWithId(cardId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            state.currentCard?.let {
                Text(text = "name: ${it.name}")
                Text(text = "archetype: ${it.details.archetype}")
                Text(text = "attribute: ${it.attribute}")
                Text(text = "level: ${it.level}")
                Text(text = "desc: ${it.details.desc}")
                Text(text = "race: ${it.details.race}")
                Text(text = "type: ${it.details.type}")
            }

            IconButton(
                onClick = { viewModel.updateFavorite() },
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Red,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            /*ItemCard(
                imageUrl = state.currentCard?.card_images?.first()?.image_url,
                isLoading = state.isCardLoading,
            )*/
        }
    }
}