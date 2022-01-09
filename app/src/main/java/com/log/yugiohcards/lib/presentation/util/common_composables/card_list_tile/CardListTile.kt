package com.log.yugiohcards.lib.presentation.util.common_composables.card_list_tile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.presentation.util.common_composables.ItemCard
import timber.log.Timber

data class TileStateStatic(
    val image_url: String,
    val height: State<Dp>,
    val debug: String,
)

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CardListTile(
    card: Card,
    cardOnClick: () -> Unit = {},
) {
    val monsterCard: Boolean = card.details.attribute != null

    var expanded by remember { mutableStateOf(false) }


//    val height = animateDpAsState(if (expanded) 128.dp else 64.dp)

    val state = TileStateStatic(
        image_url = if (expanded) card.card_images.first().image_url else card.card_images.first().image_url_small,
        height = animateDpAsState(if (expanded) 192.dp else 64.dp),
        debug = if (expanded) "expanded" else "not expanded",
    )


    Card(
        modifier = Modifier
            .padding(8.dp, 6.dp)
            .fillMaxWidth()
            .height(state.height.value),
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp,
        onClick = {
            Timber.d("asdfasdfasdfa: ${state.debug}")
            expanded = !expanded
            Timber.d("asdfasdfasdfa: ${state.debug}")
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ItemCard(
                imageUrl = state.image_url,
                cornerRadius = 2,
                onClick = cardOnClick,
            )
            Spacer(modifier = Modifier.width(8.dp))

            val expandedModifier: Modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)

            if (monsterCard)
                MonsterCard(
                    modifier = expandedModifier,
                    card = card,
                    expanded = expanded,
                )
            else
                STCard(
                    modifier = expandedModifier,
                    card = card,
                    expanded = expanded,
                )
        }
    }
}



