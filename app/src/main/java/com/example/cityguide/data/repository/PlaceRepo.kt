package com.example.cityguide.data.repository

import com.example.cityguide.data.local.res.PlaceDao
import com.example.cityguide.data.local.res.PlaceEntity
import kotlinx.coroutines.flow.Flow

class PlaceRepo (private val dao: PlaceDao){

    suspend fun insertPlace(place: PlaceEntity) {
        dao.insertPlace(place)
    }

    suspend fun deletePlace(place: PlaceEntity) {
        dao.deletePlace(place)
    }

    fun getAllPlaces(): Flow<List<PlaceEntity>> {
        return dao.getAllPlaces()
    }


    fun getPlacesByBookmark(isBookmarked: Boolean): Flow<List<PlaceEntity>> {
        return dao.getPlacesByBookmark(isBookmarked)
    }

    suspend fun updateBookmark(id: Int, isBookmarked: Boolean) {
        dao.updateBookmark(id, isBookmarked)
    }


}