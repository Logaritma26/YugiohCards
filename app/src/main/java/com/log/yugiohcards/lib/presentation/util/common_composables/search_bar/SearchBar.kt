package com.log.yugiohcards.lib.presentation.util.common_composables.search_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.log.yugiohcards.lib.domain.use_case.card.GetFilterListUseCase
import com.log.yugiohcards.lib.presentation.util.common_composables.text_field.CustomTextField
import com.log.yugiohcards.lib.presentation.util.common_composables.text_field.TextFieldState

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun SearchBar(
    state: TextFieldState,
    filterMap: Map<GetFilterListUseCase.FilterType, ArrayList<String>>,
    setFilter: (String?, GetFilterListUseCase.FilterType) -> Unit,
    getFilterKey: (GetFilterListUseCase.FilterType) -> String?,
    filterState: Boolean,
    changeFilterState: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CustomTextField(
                modifier = Modifier.weight(1f, true),
                state = state,
                onSearchCallback = { text ->
                    setFilter(
                        text,
                        GetFilterListUseCase.FilterType.QUERY
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Card(
                modifier = Modifier
                    .height(42.dp)
                    .width(42.dp),
                shape = RoundedCornerShape(size = 21.dp),
                elevation = 8.dp,
                onClick = { changeFilterState() },
            ) {
                Crossfade(
                    targetState = filterState
                ) { state ->
                    if (state) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Search Icon"
                        )
                    } else {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(6.dp))
        AnimatedVisibility(
            visible = filterState,
        ) {
            FilterCard(
                filterMap = filterMap,
                setFilter = setFilter,
                getFilterKey = getFilterKey,
            )
        }
    }
}