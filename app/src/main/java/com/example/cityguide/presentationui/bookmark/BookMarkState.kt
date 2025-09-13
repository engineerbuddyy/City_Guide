package com.example.cityguide.presentationui.bookmark

import com.example.cityguide.data.local.res.PlaceEntity

data class BookmarkScreenState(
    val places: List<PlaceEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)