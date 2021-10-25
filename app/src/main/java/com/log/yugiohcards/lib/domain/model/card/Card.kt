package com.log.yugiohcards.lib.domain.model.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.log.yugiohcards.lib.data.remote.card.dto.CardImage
import com.log.yugiohcards.lib.data.remote.card.dto.CardPrice

@Entity
data class Card(
    val archetype: String,
    val atk: Int,
    val attribute: String,
    val card_images: List<CardImage>,
    val card_prices: List<CardPrice>,
    val def: Int,
    val desc: String,
    val id: Int,
    val level: Int,
    val name: String,
    val race: String,
    val type: String,
    @PrimaryKey val roomId: Int? = null,
)