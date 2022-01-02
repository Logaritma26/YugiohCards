package com.log.yugiohcards.lib.presentation.util.common_composables.card_list_tile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.log.yugiohcards.R
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.presentation.util.card_enums.Type
import com.log.yugiohcards.lib.presentation.util.isCardTypeOf
import com.log.yugiohcards.lib.presentation.util.typeSuffixed


@Composable
fun MonsterCard(
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
            attribute = card.attribute!!
        )
        Spacer(modifier = Modifier.width(4.dp))
        if (card.level > 0)
            CardLevel(
                levelCount = card.level,
                isXyz = card.details.type!!.isCardTypeOf(Type.XYZ),
            )
        if (expanded) {
            RaceSpecs(
                race = card.details.race!!,
                type = card.details.type!!,
                arc = card.details.archetype,
            )
            Stats(atk = card.atk, def = card.def)
            Description(desc = card.details.desc!!)
        }
    }
}

@Composable
private fun CardTitle(
    name: String,
    attribute: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(weight = 1f),
            maxLines = 1,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(attribute)
    }
}

@Composable
private fun CardLevel(
    levelCount: Int,
    isXyz: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp),
        horizontalArrangement = if (isXyz) Arrangement.Start else Arrangement.End,
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
private fun RaceSpecs(
    race: String,
    type: String,
    arc: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        arc?.let {
            Text(
                text = it,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 6.dp, top = 2.dp)
            )
        }
        Text(
            text = "[$race/${type.typeSuffixed()}]",
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.Start),
            maxLines = 1,
        )
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
        Text(text = "ATK/$atk")
        Text(text = "DEF/$def")
    }
}

@Composable
private fun Description(desc: String) = Text(
    text = desc,
    modifier = Modifier.padding(vertical = 4.dp)
)
