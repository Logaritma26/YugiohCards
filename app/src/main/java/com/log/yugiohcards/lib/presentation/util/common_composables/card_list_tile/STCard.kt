package com.log.yugiohcards.lib.presentation.util.common_composables.card_list_tile

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.log.yugiohcards.lib.domain.model.card.Card


@Composable
fun STCard(
    modifier: Modifier,
    card: Card,
    expanded: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .then(
                if (expanded)
                    modifier
                else
                    Modifier
            )
            .padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        CardTitle(
            name = card.name,
            type = card.details.type,
            race = card.details.race,
        )
        if (expanded) {
            card.details.archetype?.let {
                Archetype(arc = it)
            }
            Description(desc = card.details.desc)
        }
    }
}


@Composable
private fun CardTitle(
    name: String,
    type: String,
    race: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(weight = 1f),
            maxLines = 1,
            style = MaterialTheme.typography.body1,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = type,
                style = MaterialTheme.typography.body2,
            )
            Text(
                text = race,
                style = MaterialTheme.typography.body2,
            )
        }

    }
}


@Composable
private fun Archetype(arc: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = arc,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 6.dp, top = 2.dp),
            style = MaterialTheme.typography.subtitle2,
        )
    }
}


@Composable
private fun Description(desc: String) = Text(
    text = desc,
    modifier = Modifier.padding(vertical = 4.dp),
    style = MaterialTheme.typography.body2,
)

