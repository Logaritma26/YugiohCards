package com.log.yugiohcards.lib.presentation.screens.saved_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.log.yugiohcards.lib.domain.use_case.card.GetFavoriteCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getFavoriteCardsUseCase: GetFavoriteCardsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SavedState())
    val state: State<SavedState> get() = _state

    suspend fun getFavoriteCards() {
        Timber.d("started favs")
        getFavoriteCardsUseCase().onEach {
            Timber.d("first each favs")
            _state.value = state.value.copy(cards = it)
        }.launchIn(viewModelScope)
    }

}