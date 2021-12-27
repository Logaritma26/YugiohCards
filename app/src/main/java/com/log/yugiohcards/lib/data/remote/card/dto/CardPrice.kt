package com.log.yugiohcards.lib.data.remote.card.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardPrice(
    val amazon_price: String,
    val cardmarket_price: String,
    val coolstuffinc_price: String,
    val ebay_price: String,
    val tcgplayer_price: String
): Parcelable

/*
fun emptyCardPrice(): CardPrice {
    return CardPrice(
        amazon_price = "empty",
        cardmarket_price = "empty",
        coolstuffinc_price = "empty",
        ebay_price = "empty",
        tcgplayer_price = "empty",
    )
}*/
