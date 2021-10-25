package com.log.yugiohcards.lib.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.log.yugiohcards.R

sealed class BottomRoutes(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val description: String?,
) {
    object Saved : BottomRoutes("home", R.string.route_home, Icons.Filled.Bookmarks, null)
    object Home : BottomRoutes("saved", R.string.route_saved, Icons.Filled.Home, null)
    object Search : BottomRoutes("search", R.string.route_search, Icons.Filled.Search, null)
}