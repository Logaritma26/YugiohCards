package com.log.yugiohcards.lib.data.remote.card.dto

import com.log.yugiohcards.lib.data.local.card.CardEntity
import com.log.yugiohcards.lib.domain.model.card.CardDetails


// TODO type nonnull

data class CardDto(
    val archetype: String?,
    val atk: Int,
    val attribute: String?,
    val banlist_info: BanlistInfo?,
    val card_images: List<CardImage>,
    val card_prices: List<CardPrice>,
    val card_sets: List<CardSet>,
    val def: Int,
    val desc: String,
    val id: Int,
    val level: Int,
    val linkmarkers: List<String>,
    val linkval: Int,
    val name: String,
    val race: String,
    val scale: Int,
    val type: String,
)

/*fun CardDto.toCard(): Card {
    return Card(
        atk = atk,
        attribute = attribute,
        card_images = card_images,
        def = def,
        id = id,
        level = level,
        name = name,
        details = CardDetails(
            archetype = archetype,
            card_prices = card_prices,
            desc = desc,
            race = race,
            type = type,
        ),
    )
}*/

fun CardDto.toCardEntity(): CardEntity {
    return CardEntity(
        atk = atk,
        attribute = attribute,
        card_images = card_images,
        def = def,
        id = id,
        level = level,
        name = name,
        details = CardDetails(
            archetype = archetype,
            card_prices = card_prices,
            desc = desc,
            race = race,
            type = type,
        ),
    )
}