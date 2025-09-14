package com.example.cityguide.presentationui.home

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cityguide.data.local.res.PlaceEntity
import com.example.cityguide.Navigation.Routes
import com.example.cityguide.presentationui.animation.HeartConfig
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.ui.res.painterResource
import com.example.cityguide.R
import com.example.cityguide.presentationui.RatingBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    // STEP 4A: hearts state + coroutine scope + config
    val hearts = remember { mutableStateListOf<Int>() }
    val scope = rememberCoroutineScope()
    val config = remember { HeartConfig(radiusMultiplier = 1f, delayDuration = 120L) }

    // Categories list
    val categories = listOf(
        "Temple",
        "Heritage Sites",
        "Forts & Palaces",
        "Museums",
        "Art Galleries",
        "Monuments",
        "Lakes",
        "Mountains",
        "Park",
        "Food"

    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.height(125.dp),
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "City Guide",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Your Favourite Destination",
                            fontSize = 15.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                        )
                    }
                },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(end = 12.dp, top = 10.dp)
                    ) {
                        IconButton(onClick = { navController.navigate(Routes.BookmarkScreen.route) }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Bookmarks",
                                modifier = Modifier.size(35.dp),
                                tint = Color(0xFFda0c49)
                            )
                        }
                        IconButton(onClick = { navController.navigate(Routes.AddScreen.route) }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Place",
                                modifier = Modifier.size(38.dp),
                                tint = Color.Black
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(text = state.error ?: "Unknown Error")
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    // 🔍 Search Bar
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(18.dp),
                        placeholder = { Text("Search") },
                        singleLine = true
                    )

                    // 🔹 Category Chips
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(categories.size) { index ->
                            val category = categories[index]
                            FilterChip(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .height(40.dp),
                                selected = selectedCategory == category,
                                onClick = {
                                    selectedCategory =
                                        if (selectedCategory == category) "" else category
                                },
                                label = { Text(category) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFda0c49),
                                    selectedLabelColor = Color.White
                                ),
                                shape = RoundedCornerShape(18.dp),

                                )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 📜 Filtered list
                    val filteredPlaces = state.places.filter {
                        (selectedCategory.isBlank() || it.category.equals(
                            selectedCategory,
                            ignoreCase = true
                        )) &&
                                it.placeName.contains(searchQuery, ignoreCase = true)
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(filteredPlaces) { place ->
                            PlaceItem(place, navController, viewModel)
                        }
                    }
                }
            }
        }

    }

}


@Composable
fun PlaceItem(
    place: PlaceEntity,
    navController: NavController,
    HomeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        navController.navigate(Routes.CardItem.createRoute(place.id))
                    }
                )
            },
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 🖼️ Place image on left
            place.image?.let {
                Image(
                    bitmap = BitmapFactory.decodeByteArray(it, 0, it.size).asImageBitmap(),
                    contentDescription = place.placeName,
                    modifier = Modifier
                        .size(70.dp)
                        .aspectRatio(1f)
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            } ?: Box( // placeholder if no image
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.noimg),
                    contentDescription = "Sample Image",
                    modifier = Modifier.size(45.dp)
                )

            }

            // 📋 Details on right
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),

            ) {
                Text(
                    text = place.placeName,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,

                    )
                )

                Spacer(modifier = Modifier.height(7.dp))

                // ⭐ Use RatingBar instead of text
                RatingBar(rating = place.rating ?: 0.0)
            }
            // 🗑️ Delete Button
            IconButton(
                onClick = { HomeScreenViewModel.deletePlace(place) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Place",
                    tint = Color.Blue
                )
            }
        }
    }
}

