package com.log.yugiohcards.lib.data.remote.card.dto

data class CardPrice(
    val amazon_price: String,
    val cardmarket_price: String,
    val coolstuffinc_price: String,
    val ebay_price: String,
    val tcgplayer_price: String
)