package com.log.yugiohcards.lib.presentation.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.log.yugiohcards.core.util.Resource
import com.log.yugiohcards.lib.domain.use_case.card.GetRandomCardUseCase
import com.log.yugiohcards.lib.domain.use_case.card.SaveAllRemoteCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomCardUseCase: GetRandomCardUseCase,
    private val saveAllRemoteCardsUseCase: SaveAllRemoteCardsUseCase,
) : ViewModel() {

    init {
        saveAllCardsIfRoomEmpty()
    }

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> get() = _state

    fun getCard() {
        getRandomCardUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(currentCard = it.data)
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isCardLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = true,
                        errorMessage = it.message ?: "An unexpected error occured",
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveAllCardsIfRoomEmpty() = viewModelScope.launch { saveAllRemoteCardsUseCase() }

}