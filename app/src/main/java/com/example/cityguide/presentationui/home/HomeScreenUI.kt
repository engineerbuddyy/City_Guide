package com.example.cityguide.presentationui.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cityguide.data.local.res.PlaceEntity
import com.example.cityguide.Navigation.Routes

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            ) {
                // ❤️ Heart Button for bookmarks
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.BookmarkScreen.route) },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Bookmarks",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }

                // ➕ Add Button
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.AddScreen.route) },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Place",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text(text = state.error ?: "Unknown Error")
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    items(state.places) { place ->
                        PlaceItem(place, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceItem(place: PlaceEntity, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        navController.navigate(Routes.CardItem.createRoute(place.id))
                    }
                )
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = place.placeName, style = MaterialTheme.typography.titleMedium)
            Text(text = "Rating: ${place.rating}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
