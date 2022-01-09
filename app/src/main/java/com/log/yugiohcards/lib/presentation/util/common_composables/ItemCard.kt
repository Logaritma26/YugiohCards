package com.log.yugiohcards.lib.presentation.util.common_composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ItemCard(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
    onDoubleClick: () -> Unit = {},
    fraction: Float = 1f,
    ratio: Float = 0.69f,
    minCard: Int = 0,
    maxCard: Int = 1000,
    cornerRadius: Int = 6,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = (imageUrl != null || isLoading),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Card(
                modifier = Modifier
                    .heightIn(min = minCard.dp, max = maxCard.dp)
                    .fillMaxHeight(fraction = fraction.coerceIn(0.0f, 1.0f))
                    .aspectRatio(ratio = ratio)
                    .combinedClickable(
                        enabled = true,
                        onClick = { onClick() },
                        onDoubleClick = { onDoubleClick() }
                    ),
                shape = RoundedCornerShape(size = cornerRadius.dp),
                elevation = 14.dp,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .aspectRatio(ratio = 1f),
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 7.dp,
                    )
                } else if (imageUrl != null) {
                    Glide(imageModel = imageUrl)
                }
            }
        }
    }
}