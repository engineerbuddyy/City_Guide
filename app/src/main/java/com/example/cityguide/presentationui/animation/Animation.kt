package com.example.cityguide.presentationui.animation


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.random.Random
import kotlin.math.roundToInt

@Composable
fun FloatingHeart(
    key: Int,
    config: HeartConfig,
    // start offsets in pixels relative to the parent Box anchor (optional)
    startX: Int = 0,
    startY: Int = 0,
    onAnimationEnd: () -> Unit
) {
    // small horizontal angle spread so hearts don't all fall straight
    val angle = remember { Random.nextDouble(-40.0, 40.0) }
    // distance hearts will travel downwards (tweak to taste)
    val baseRadius = remember { Random.nextDouble(120.0, 420.0).toFloat() }
    val radius = baseRadius * config.radiusMultiplier

    val xOffset = remember { Animatable(0f) }
    val yOffset = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(key) {
        val duration = 1400

        launch {
            // small horizontal drift
            xOffset.animateTo(
                targetValue = (radius * cos(Math.toRadians(angle))).toFloat(),
                animationSpec = tween(durationMillis = duration, easing = LinearEasing)
            )
        }

        launch {
            // POSITIVE y -> move down; add offset so hearts fall visibly
            yOffset.animateTo(
                targetValue = (radius * 0.9f) + 250f,
                animationSpec = tween(durationMillis = duration, easing = LinearEasing)
            )
        }

        launch {
            // slight rotation for flair
            rotation.animateTo(
                targetValue = Random.nextDouble(-25.0, 25.0).toFloat(),
                animationSpec = tween(durationMillis = duration)
            )
        }

        launch {
            // fade out
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = duration)
            )
            // When fade completes, notify parent to remove this heart id
            onAnimationEnd()
        }
    }

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        tint = Color(0xFFda0c49).copy(alpha = alpha.value),
        modifier = Modifier
            .offset {
                IntOffset(
                    x = (startX + xOffset.value).roundToInt(),
                    y = (startY + yOffset.value).roundToInt()
                )
            }
            .size(28.dp)
            .graphicsLayer {
                rotationZ = rotation.value
            }
    )
}
