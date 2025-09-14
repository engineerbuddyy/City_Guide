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
    val image: ByteArray? = null,
    val rating: Double?=0.0 ,
    val address: String,
    val isBookmarked: Boolean = false
)
