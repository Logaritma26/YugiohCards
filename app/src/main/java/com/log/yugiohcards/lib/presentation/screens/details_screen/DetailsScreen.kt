package com.log.yugiohcards.lib.presentation.screens.details_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.log.yugiohcards.R
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.presentation.util.common_composables.ItemCard
import com.log.yugiohcards.lib.presentation.util.typeSuffixed

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
        contentAlignment = Alignment.TopCenter,
    ) {
        AnimatedVisibility(visible = state.currentCard != null) {

            val card: Card = state.currentCard!!
            val monsterCard: Boolean = card.details.attribute != null

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyRow(
                    state = rememberLazyListState(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(12.dp),
                ) {
                    items(card.card_images) { image ->
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            ItemCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f),
                                imageUrl = image.image_url,
                                isLoading = state.isCardLoading,
                            )
                        }
                    }
                }
                Text(
                    text = card.name,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    style = MaterialTheme.typography.body1,
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (monsterCard) {
                    CardLevel(levelCount = card.level)
                    Spacer(modifier = Modifier.height(24.dp))
                }

                RaceSpecs(
                    race = card.details.race,
                    type = card.details.type,
                    arc = card.details.archetype,
                )

                if (monsterCard) {
                    Stats(atk = card.atk, def = card.def)
                }

                IconButton(
                    onClick = { viewModel.updateFavorite() },
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = if (card.favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                    )
                }

                Text(
                    modifier = Modifier.padding(12.dp),
                    text = card.details.desc,
                    style = MaterialTheme.typography.body2,
                )
            }
        }

    }
}


@Composable
private fun RaceSpecs(
    race: String,
    type: String,
    arc: String?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "[$race/${type.typeSuffixed()}]",
            style = MaterialTheme.typography.subtitle2,
        )

        arc?.let {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}

@Composable
private fun CardLevel(levelCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 1..levelCount) {
            Image(
                painterResource(R.drawable.ic_level_star),
                contentDescription = "Level Star",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }
    }
}

@Composable
private fun Stats(
    atk: Int,
    def: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "ATK/$atk",
            style = MaterialTheme.typography.subtitle2,
        )
        Text(
            text = "DEF/$def",
            style = MaterialTheme.typography.subtitle2,
        )
    }
}
