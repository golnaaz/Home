package com.golnaz.home.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.golnaz.home.navigation.NavRoute.Companion.ARG_ID
import com.golnaz.home.ui.screen.ItemDetailScreen
import com.golnaz.home.ui.screen.ItemListScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.MainScreen.path,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        addMainScreen(navController, this)

        addDetailsScreen(navController, this)
    }
}

private fun addMainScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.MainScreen.path) { navBackStackEntry ->
        ItemListScreen(
            viewModel = koinViewModel(),
            navigateToDetail = { id ->
                navController.navigate(NavRoute.DetailsScreen.withArgs(id.toString()))
            }
        )
    }
}

fun addDetailsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.DetailsScreen.path + "/{$ARG_ID}",
        arguments = listOf(navArgument(ARG_ID) {
            type = NavType.StringType
            nullable = false
        }
        )
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getString(ARG_ID)?.let {
            ItemDetailScreen(
                viewModel = koinViewModel(),
                popBackStack = { navController.popBackStack() },
                id = it,
            )
        }
    }
}