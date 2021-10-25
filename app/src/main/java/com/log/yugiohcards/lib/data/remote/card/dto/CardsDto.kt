package com.log.yugiohcards.lib.data.remote.card.dto

import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.model.card.Cards

data class CardsDto(
    val data: List<CardDto>
)

fun CardsDto.toCards(): Cards {
    val netData: MutableList<Card> = mutableListOf()
    this.data.forEach {
        netData.add(it.toCard())
    }
    return Cards(netData.toList())
}
