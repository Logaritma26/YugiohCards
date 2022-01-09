package com.log.yugiohcards.lib.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.log.yugiohcards.lib.presentation.navigation.Navigation
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.SAVED
import com.log.yugiohcards.lib.presentation.navigation.ObjectRoutes.SEARCH
import com.log.yugiohcards.ui.theme.YugiohCardsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            YugiohCardsTheme {
                val navController: NavHostController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                Scaffold(
                    floatingActionButton = {
                        when (navBackStackEntry?.destination?.route) {
                            SAVED.path() -> {
                                FloatingActionButton(
                                    onClick = {
                                        routeChanger(
                                            navBackStackEntry = navBackStackEntry,
                                            navController = navController,
                                        )
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Switch to search page",
                                    )
                                }
                            }
                            SEARCH.path() -> {
                                FloatingActionButton(
                                    onClick = {
                                        routeChanger(
                                            navBackStackEntry = navBackStackEntry,
                                            navController = navController,
                                        )
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Switch to favorites page",
                                    )
                                }
                            }
                        }

                    }
                ) {
                    Navigation(
                        navController = navController,
                        modifier = Modifier.padding(it),
                    )
                }
            }
        }
    }

    private fun routeChanger(
        navBackStackEntry: NavBackStackEntry?,
        navController: NavController,
    ) {
        when (navBackStackEntry?.destination?.route) {
            SAVED.path() -> {
                navController.navigate(SEARCH.path())
            }
            SEARCH.path() -> {
                navController.navigate(SAVED.path())
            }
        }
    }
}
