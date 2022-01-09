package com.log.yugiohcards.lib.presentation.util.common_composables.search_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.log.yugiohcards.lib.domain.use_case.card.GetFilterListUseCase

@ExperimentalMaterialApi
@Composable
fun FilterCard(
    filterMap: Map<GetFilterListUseCase.FilterType, ArrayList<String>>,
    setFilter: (String?, GetFilterListUseCase.FilterType) -> Unit,
    getFilterKey: (GetFilterListUseCase.FilterType) -> String?,
) {
    var visibleType by remember { mutableStateOf<GetFilterListUseCase.FilterType?>(null) }

    fun changeOtherFiltersVisibility(filterType: GetFilterListUseCase.FilterType?) {
        visibleType = filterType
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState(),
        ) {
            items(filterMap.toList()) { pair ->
                AnimatedVisibility(visible = pair.first == visibleType || visibleType == null) {
                    FilterTile(
                        pair = pair,
                        setFilter = setFilter,
                        getFilterKey = getFilterKey,
                        changeOtherFiltersVisibility = { filterType ->
                            changeOtherFiltersVisibility(
                                filterType
                            )
                        }
                    )
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
private fun FilterTile(
    pair: Pair<GetFilterListUseCase.FilterType, ArrayList<String>>,
    setFilter: (String?, GetFilterListUseCase.FilterType) -> Unit,
    getFilterKey: (GetFilterListUseCase.FilterType) -> String?,
    changeOtherFiltersVisibility: (GetFilterListUseCase.FilterType?) -> Unit,
) {
    val filterType: GetFilterListUseCase.FilterType = pair.first
    val filterKey: String? = getFilterKey(filterType)

    var expanded by remember { mutableStateOf(false) }
    val height = animateDpAsState(if (expanded) 640.dp else 64.dp)


    Card(
        modifier = Modifier
            .padding(8.dp, 10.dp)
            .fillMaxWidth()
            .height(height.value),
        shape = RoundedCornerShape(8.dp),
        elevation = 6.dp,
        onClick = {
            if (expanded) {
                changeOtherFiltersVisibility(null)
            } else {
                changeOtherFiltersVisibility(pair.first)
            }
            expanded = !expanded
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = filterType.toString())
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = filterKey ?: "",
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(weight = 1f),
                    maxLines = 1,
                )
                if (filterKey != null) {
                    IconButton(
                        onClick = { setFilter(null, pair.first) },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clean Filter"
                        )
                    }
                }
            }
            AnimatedVisibility(visible = expanded) {
                LazyColumn(state = rememberLazyListState()) {

                    item {
                        Divider()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(pair.second) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .clickable {
                                    setFilter(item, pair.first)
                                },
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 12.dp),
                                text = item,
                            )
                        }
                    }
                }
            }
        }
    }
}

