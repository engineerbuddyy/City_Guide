package com.example.cityguide.data.local.res

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val placeName: String,
    val category: String,
    val description: String,
    //Save path not image
    val imageUri: String? ,
    val rating: Double?=0.0 ,
    val address: String,
    val isBookmarked: Boolean = false
)
