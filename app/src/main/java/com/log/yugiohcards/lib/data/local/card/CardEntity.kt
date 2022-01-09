package com.log.yugiohcards.lib.data.local.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.log.yugiohcards.lib.data.remote.card.dto.CardImage
import com.log.yugiohcards.lib.data.remote.card.dto.CardPrice
import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.model.card.CardDetails

@Entity
data class CardEntity(
    val atk: Int,
    val def: Int,
    val attribute: String?,
    val archetype: String?,
    val race: String,
    val type: String,
    val level: Int,
    val favorite: Boolean = false,
    val card_images: List<CardImage>,
    val id: Int,
    val name: String,
    val card_prices: List<CardPrice>,
    val desc: String,
    @PrimaryKey val roomId: Int? = null,
) {
    fun toCard(): Card = Card(
        atk = atk,
        def = def,
        card_images = card_images,
        id = id,
        level = level,
        name = name,
        details = CardDetails(
            race = race,
            type = type,
            card_prices = card_prices,
            desc = desc,
            archetype = archetype,
            attribute = attribute
        ),
        favorite = favorite,
    )
}
