package com.example.cityguide.presentationui.add

data class AddScreenState(
    val placeName: String = "",
    val category: String = "",
    val description: String = "",
    val address: String = "",
    val rating: String = "",
    val image: ByteArray? = null,
    val isSaving: Boolean = false,
    val error: String? = null
)
