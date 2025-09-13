package com.example.cityguide.Navigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object AddScreen : Routes("add_screen")
    object CardItem : Routes("card_item/{placeId}") {
        fun createRoute(placeId: Int) = "card_item/$placeId"
    }
    object SearchScreen : Routes("search_screen")
    object BookmarkScreen : Routes("bookmark_screen")
}
