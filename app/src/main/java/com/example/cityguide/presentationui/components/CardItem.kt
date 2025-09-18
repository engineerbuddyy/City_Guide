package com.example.cityguide.presentationui.components


import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import coil.compose.AsyncImage
import com.example.cityguide.presentationui.AddressLink
import com.example.cityguide.presentationui.RatingBar
import java.io.File

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
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White,
                    )

                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFda0c49)
                ),
                modifier = Modifier.height(90.dp)
            )
        }
    ) { padding ->
        place?.let { p ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = p.placeName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))


                p.imageUri?.let { uri ->
                    AsyncImage(
                        model = File(place!!.imageUri),
                        contentDescription = p.placeName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = p.category,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFFf0f0f0),
                            labelColor = Color.DarkGray
                        )
                    )

                    RatingBar(rating = p.rating ?: 0.0)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = p.description,
                    fontSize = 20.sp,
                    color = Color.DarkGray,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                AddressLink(address = place!!.address)

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.toggleBookmark(p) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (p.isBookmarked) Color(0xFFda0c49) else Color.DarkGray
                    )
                ) {
                    Text(
                        text = if (p.isBookmarked) "Remove Bookmark" else "Bookmark Place",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
