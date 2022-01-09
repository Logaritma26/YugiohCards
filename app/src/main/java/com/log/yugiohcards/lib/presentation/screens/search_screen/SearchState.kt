package com.log.yugiohcards.lib.presentation.screens.search_screen

import com.log.yugiohcards.lib.domain.model.card.Card
import com.log.yugiohcards.lib.domain.use_case.card.GetFilterListUseCase

data class SearchState(
    val filterMap: Map<GetFilterListUseCase.FilterType, ArrayList<String>> = mapOf(),
    val filteredCards: List<Card> = listOf(),
    val allCards: List<Card> = listOf(),
)

data class FilterState(
    val queryKey: String? = null,
    val attributeKey: String? = null,
    val archetypeKey: String? = null,
    val raceKey: String? = null,
    val typeKey: String? = null,
)
