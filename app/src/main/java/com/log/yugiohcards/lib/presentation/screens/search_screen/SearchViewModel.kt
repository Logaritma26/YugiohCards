package com.log.yugiohcards.lib.presentation.screens.search_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.log.yugiohcards.lib.domain.use_case.card.GetAllCardsUseCase
import com.log.yugiohcards.lib.domain.use_case.card.GetFilterListUseCase
import com.log.yugiohcards.lib.domain.use_case.card.GetFilteredCards
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFilterListUseCase: GetFilterListUseCase,
    private val getFilteredCards: GetFilteredCards,
    private val getAllCardsUseCase: GetAllCardsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> get() = _state

    private val _filterKeyState = mutableStateOf(FilterState())
    val filterKeyState: State<FilterState> get() = _filterKeyState


    fun setFilter(value: String?, filterType: GetFilterListUseCase.FilterType) {
        when (filterType) {
            GetFilterListUseCase.FilterType.ATTRIBUTE -> _filterKeyState.value =
                _filterKeyState.value.copy(attributeKey = value)
            GetFilterListUseCase.FilterType.ARCHETYPE -> _filterKeyState.value =
                _filterKeyState.value.copy(archetypeKey = value)
            GetFilterListUseCase.FilterType.RACE -> _filterKeyState.value =
                _filterKeyState.value.copy(raceKey = value)
            GetFilterListUseCase.FilterType.TYPE -> _filterKeyState.value =
                _filterKeyState.value.copy(typeKey = value)
            GetFilterListUseCase.FilterType.QUERY -> _filterKeyState.value =
                _filterKeyState.value.copy(queryKey = value)
        }
        getFilteredCards()
    }

    suspend fun getFilterList() {
        val filters = getFilterListUseCase()
        _state.value = _state.value.copy(filterMap = filters)
    }

    suspend fun getAllCards() {
        val cards = getAllCardsUseCase()
        _state.value = _state.value.copy(
            allCards = cards,
            filteredCards = cards,
        )
        getFilteredCards()

    }

    private fun getFilteredCards() {
        val cards = getFilteredCards(
            allCards = _state.value.allCards,
            query = _filterKeyState.value.queryKey,
            archetype = _filterKeyState.value.archetypeKey,
            attribute = _filterKeyState.value.attributeKey,
            race = _filterKeyState.value.raceKey,
            type = _filterKeyState.value.typeKey,
        )
        _state.value = _state.value.copy(filteredCards = cards)
    }
}