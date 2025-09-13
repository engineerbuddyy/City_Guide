package com.example.cityguide.presentationui.home


import com.example.cityguide.data.local.res.PlaceEntity

data class HomeScreenState(
    val places: List<PlaceEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
