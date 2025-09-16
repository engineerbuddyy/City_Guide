package com.example.cityguide.presentationui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(rating: Double, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        val fullStars = rating.toInt()
        val hasHalfStar = (rating - fullStars) in 0.1..0.9

        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107), // gold
                modifier = Modifier.size(18.dp)
            )
        }

        if (hasHalfStar) {
            Icon(
                imageVector = Icons.Rounded.StarHalf,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
