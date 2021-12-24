package com.log.yugiohcards.lib.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.HOME
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.SAVED
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.SEARCH

// TODO delete description at the end if not used
sealed class BottomRoutes(
    val route: Route,
    val icon: ImageVector,
    val label: String,
    val description: String?,
) {
    object Saved : BottomRoutes(SAVED, Icons.Filled.Bookmarks, label = "Saved",null)
    object Home : BottomRoutes(HOME, Icons.Filled.Home, label = "Home",null)
    object Search : BottomRoutes(SEARCH, Icons.Filled.Search, label = "Search",null)
}