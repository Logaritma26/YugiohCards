package com.log.yugiohcards.lib.presentation.screens.details_screen

import com.log.yugiohcards.lib.domain.model.card.Card

data class DetailState (
    val currentCard: Card? = null,
    val isCardLoading: Boolean = false,
    val error: Boolean = false,
    val errorMessage: String? = null,
)
