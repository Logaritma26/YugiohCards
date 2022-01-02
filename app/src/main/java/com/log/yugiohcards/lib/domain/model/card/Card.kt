package com.log.yugiohcards.lib.domain.model.card

import android.os.Parcelable
import com.log.yugiohcards.lib.data.remote.card.dto.CardImage
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val atk: Int,
    val def: Int,
    val attribute: String?,
    val card_images: List<CardImage>,
    val id: Int,
    val level: Int,
    val name: String,
    val details: CardDetails,
    val favorite: Boolean,
) : Parcelable