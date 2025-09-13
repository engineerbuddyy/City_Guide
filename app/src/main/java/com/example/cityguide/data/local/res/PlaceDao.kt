package com.example.cityguide.data.local.res

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao{
    @Upsert
    suspend fun insertPlace(place: PlaceEntity)

    @Delete
    suspend fun deletePlace(place: PlaceEntity)

    @Query("SELECT * FROM places")
    fun getAllPlaces(): Flow<List<PlaceEntity>>

//    suspend fun getPlaceById(id: Int): PlaceEntity?

    @Query("SELECT * FROM places WHERE placeName = :name")
    suspend fun getPlaceByName(name: String): PlaceEntity?

    @Update
    suspend fun updatePlace(place: PlaceEntity)

    @Query("DELETE FROM places")
    suspend fun deleteAllPlaces()

    @Query("SELECT * FROM places WHERE category = :category")
    fun getPlacesByCategory(category: String): Flow<List<PlaceEntity>>

//    suspend fun getPlacesByRating(rating: Double): List<PlaceEntity>

    @Query("UPDATE places SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmark(id: Int, isBookmarked: Boolean)

    @Query("SELECT * FROM places WHERE isBookmarked = :isBookmarked")
    fun getPlacesByBookmark(isBookmarked: Boolean): Flow<List<PlaceEntity>>

    @Query("SELECT * FROM places WHERE placeName LIKE '%' || :query || '%' ")
    fun getPlacesBySearchQuery(query: String): Flow<List<PlaceEntity>>

//    suspend fun getPlacesByLocation(latitude: Double, longitude: Double): List<PlaceEntity>

//    suspend fun getPlacesByDistance(distance: Double): List<PlaceEntity>

//    suspend fun getPlacesByPriceRange(minPrice: Double, maxPrice: Double): List<PlaceEntity>

//    suspend fun getPlacesByOpeningHours(openingHours: String): List<PlaceEntity>


}