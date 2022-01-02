package com.log.yugiohcards.lib.domain.model.card

import android.os.Parcelable
import com.log.yugiohcards.lib.data.remote.card.dto.CardPrice
import kotlinx.parcelize.Parcelize

// TODO add attribute
// TODO race type prices desc nonnull
@Parcelize
data class CardDetails(
    val race: String?,
    val type: String?,
    val card_prices: List<CardPrice>?,
    val desc: String?,
    val archetype: String?,
): Parcelable

fun emptyCardDetails(): CardDetails {
    return CardDetails(
        race = null,
        type = null,
        card_prices = null,
        desc = null,
        archetype = null,
    )
}
