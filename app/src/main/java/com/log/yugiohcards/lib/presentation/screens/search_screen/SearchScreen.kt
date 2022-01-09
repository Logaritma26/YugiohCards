package com.log.yugiohcards.lib.presentation.screens.search_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.log.yugiohcards.lib.domain.use_case.card.GetFilterListUseCase
import com.log.yugiohcards.lib.presentation.util.common_composables.ShowCardsList
import com.log.yugiohcards.lib.presentation.util.common_composables.search_bar.SearchBar
import com.log.yugiohcards.lib.presentation.util.common_composables.text_field.TextFieldState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state: SearchState = viewModel.state.value
    val filterKeyState: FilterState = viewModel.filterKeyState.value

    LaunchedEffect(key1 = true) {
        viewModel.getAllCards()
        viewModel.getFilterList()
    }

    val textState: TextFieldState = remember { TextFieldState(pHint = "Make your search") }
    var filterState by remember { mutableStateOf(false) }

    fun getFilterKey(filterType: GetFilterListUseCase.FilterType): String? = when (filterType) {
        GetFilterListUseCase.FilterType.QUERY -> filterKeyState.queryKey
        GetFilterListUseCase.FilterType.ATTRIBUTE -> filterKeyState.attributeKey
        GetFilterListUseCase.FilterType.ARCHETYPE -> filterKeyState.archetypeKey
        GetFilterListUseCase.FilterType.RACE -> filterKeyState.raceKey
        GetFilterListUseCase.FilterType.TYPE -> filterKeyState.typeKey
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        SearchBar(
            state = textState,
            filterMap = state.filterMap,
            setFilter = { value, filterType ->
                viewModel.setFilter(
                    value = value,
                    filterType = filterType
                )
            },
            getFilterKey = { filterType -> getFilterKey(filterType) },
            filterState = filterState,
            changeFilterState = { filterState = !filterState },
        )

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            visible = !filterState,
        ) {
            ShowCardsList(
                state = state.filteredCards,
                navController = navController,
            )
        }
    }
}