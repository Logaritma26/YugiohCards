package com.log.yugiohcards.lib.presentation.util.common_composables

import androidx.compose.animation.*
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.log.yugiohcards.lib.domain.model.card.Card

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ItemCard(
    card: Card?,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
    fraction: Float = 1f
) {

    AnimatedVisibility(
        modifier = Modifier.padding(12.dp),
        visible = (card != null || isLoading),
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(ratio = 1f),
                color = MaterialTheme.colors.primary,
                strokeWidth = 7.dp
            )
        } else if (card != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(fraction = fraction.coerceIn(0.0f, 1.0f))
                    .aspectRatio(ratio = 0.69f),
                shape = RoundedCornerShape(7.dp),
                elevation = 14.dp,
                onClick = { onClick() }
            ) {
                Glide(imageModel = card.card_images.first().image_url)
            }
        }
    }
}