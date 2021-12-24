package com.log.yugiohcards.lib.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.log.yugiohcards.lib.presentation.navigation.BottomRoutes
import com.log.yugiohcards.lib.presentation.navigation.Navigation
import com.log.yugiohcards.ui.theme.YugiohCardsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            YugiohCardsTheme {

                val navController: NavHostController = rememberNavController()
                val bottomNavItems = listOf(
                    BottomRoutes.Saved,
                    BottomRoutes.Home,
                    BottomRoutes.Search,
                )
                Scaffold(
                    bottomBar = {
                        BottomNavigation(elevation = 4.dp) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            bottomNavItems.forEach { item ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.description,
                                        )
                                    },
                                    label = { Text(item.label) },
                                    selected = currentDestination?.hierarchy?.any { it.route == item.route.path() } == true,
                                    onClick = {
                                        navController.navigate(item.route.path()) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // re-selecting the same item
                                            launchSingleTop = true
                                            // Restore state when re-selecting a previously selected item
                                            restoreState = true
                                        }
                                    },
                                )
                            }
                        }
                    },
                ) {
                    Navigation(
                        navController = navController,
                        modifier = Modifier.padding(it),
                    )
                }
            }
        }
    }
}
