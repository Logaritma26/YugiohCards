package com.log.yugiohcards.lib.presentation.screens.details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.log.yugiohcards.core.util.Resource
import com.log.yugiohcards.lib.domain.use_case.card.GetCardWithIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCardWithIdUseCase: GetCardWithIdUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    fun getCardWithId(id: Int) {
        getCardWithIdUseCase(id).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = DetailState(currentCard = it.data)
                }
                is Resource.Loading -> {
                    _state.value = DetailState(isCardLoading = true)
                }
                is Resource.Error -> {
                    _state.value = DetailState(
                        error = true,
                        errorMessage = it.message ?: "An unexpected error occured",
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}