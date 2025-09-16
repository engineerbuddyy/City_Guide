package com.example.cityguide.presentationui.components


import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cityguide.presentationui.RatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItemUI(
    navController: NavController, placeId: Int, viewModel: CardItemViewModel = hiltViewModel()
) {
    val place by viewModel.getPlaceById(placeId).collectAsState(initial = null)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Place Details",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFda0c49)
                )
            )
        }

    ) { padding ->
        place?.let { p ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Title
                    Card(
                        modifier = Modifier.
                        wrapContentSize(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ){
                        Text(
                            text = p.placeName,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.DarkGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),

                            textAlign = TextAlign.Center
                        )

                    }


                    Spacer(modifier = Modifier.height(12.dp))

                    // Image
                    p.image?.let {
                        Image(
                            bitmap = BitmapFactory.decodeByteArray(it, 0, it.size).asImageBitmap(),
                            contentDescription = p.placeName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // Category & Rating
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = p.category,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        // ⭐ Use RatingBar instead of text
                        RatingBar(rating = place!!.rating ?: 0.0, modifier = Modifier.padding(end = 8.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Text(
                        text = p.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Address
                    Text(
                        text = "📍 ${p.address}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Bookmark button
                    Button(
                        onClick = { viewModel.toggleBookmark(p) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (p.isBookmarked) MaterialTheme.colorScheme.error else Color.DarkGray
                        )
                    ) {
                        Text(
                            text = if (p.isBookmarked) "Remove Bookmark" else "Bookmark Place",
                            color = Color.White
                        )
                    }
                }
            }

        }


    }
}
