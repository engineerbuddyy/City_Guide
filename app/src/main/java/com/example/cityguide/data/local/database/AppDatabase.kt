package com.example.cityguide.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cityguide.data.local.res.PlaceDao
import com.example.cityguide.data.local.res.PlaceEntity

@Database(
    entities = [PlaceEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}
