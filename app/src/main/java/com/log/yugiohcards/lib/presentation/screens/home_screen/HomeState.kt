package com.log.yugiohcards.lib.presentation.screens.home_screen

import com.log.yugiohcards.lib.domain.model.card.Card

data class HomeState(
    val currentCard: Card? = null,
    val isCardLoading: Boolean = false,
    val error: Boolean = false,
    val errorMessage: String? = null,
)
