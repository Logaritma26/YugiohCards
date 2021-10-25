package com.log.yugiohcards.lib.data.remote.card.dto

import com.log.yugiohcards.lib.domain.model.card.Card

data class CardDto(
    val archetype: String,
    val atk: Int,
    val attribute: String,
    val banlist_info: BanlistInfo,
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

fun CardDto.toCard(): Card {
    return Card(
        archetype = archetype,
        atk = atk,
        attribute = attribute,
        card_images = card_images,
        card_prices = card_prices,
        def = def,
        desc = desc,
        id = id,
        level = level,
        name = name,
        race = race,
        type = type,
    )
}