package com.log.yugiohcards.lib.data.remote.card.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardImage(
    val id: Int,
    val image_url: String,
    val image_url_small: String
): Parcelable