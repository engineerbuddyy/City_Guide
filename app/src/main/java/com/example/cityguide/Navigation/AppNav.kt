package com.example.cityguide.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cityguide.presentationui.add.AddScreenUI
import com.example.cityguide.presentationui.add.AddScreenViewModel
import com.example.cityguide.presentationui.bookmark.BookmarkScreen
import com.example.cityguide.presentationui.components.CardItemUI
import com.example.cityguide.presentationui.home.HomeScreen
import com.example.cityguide.presentationui.home.HomeScreenViewModel

@Composable
fun AppNav(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route
    ) {
        // Home Screen
        composable(Routes.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        // Add Screen
        composable(Routes.AddScreen.route) {
            AddScreenUI(navController = navController)
        }

        // CardItem Screen
        composable(
            route = "card_item/{placeId}",
            arguments = listOf(
                navArgument("placeId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getInt("placeId") ?: -1
            CardItemUI(navController = navController, placeId = placeId)
        }

        composable(Routes.BookmarkScreen.route) {
            BookmarkScreen(navController = navController)
        }

    }
}
