package com.log.yugiohcards.lib.presentation.screens.saved_screen

import com.log.yugiohcards.lib.domain.model.card.Card

data class SavedState(
    val cards: List<Card> = listOf(),

)
