package com.log.yugiohcards.lib.data.local.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.log.yugiohcards.lib.data.remote.card.dto.CardImage
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.model.card.CardDetails

@Entity
data class CardEntity(
    val atk: Int,
    val def: Int,
    val attribute: String,
    val card_images: List<CardImage>,
    val id: Int,
    val level: Int,
    val name: String,
    val details: CardDetails,
    @PrimaryKey val roomId: Int? = null,
) {
    fun toCard(): Card = Card(
        atk = atk,
        def = def,
        attribute = attribute,
        card_images = card_images,
        id = id,
        level = level,
        name = name,
        details = details,
    )
}
