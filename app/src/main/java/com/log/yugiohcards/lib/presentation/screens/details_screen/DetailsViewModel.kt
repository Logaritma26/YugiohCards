package com.log.yugiohcards.lib.presentation.screens.details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.log.yugiohcards.lib.domain.use_case.card.GetCardWithIdUseCase
import com.log.yugiohcards.lib.domain.use_case.card.UpdateFavoriteCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCardWithIdUseCase: GetCardWithIdUseCase,
    private val updateFavoriteCardUseCase: UpdateFavoriteCardUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    suspend fun getCardWithId(id: Int) {
        getCardWithIdUseCase(id).onEach {
            _state.value = DetailState(currentCard = it)
        }.launchIn(viewModelScope)
    }

    fun updateFavorite() {
        viewModelScope.launch {
            state.value.currentCard?.let {
                updateFavoriteCardUseCase(id = it.id, favorite = !it.favorite)
            }
        }
    }

}