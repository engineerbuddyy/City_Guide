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

    suspend fun getPlaceByName(name: String): PlaceEntity? {
        return dao.getPlaceByName(name)
    }

    suspend fun updatePlace(place: PlaceEntity) {
        dao.updatePlace(place)
    }

    suspend fun deleteAllPlaces() {
        dao.deleteAllPlaces()
    }

    fun getPlacesByCategory(category: String): Flow<List<PlaceEntity>> {
        return dao.getPlacesByCategory(category)
    }


    fun getPlacesByBookmark(isBookmarked: Boolean): Flow<List<PlaceEntity>> {
        return dao.getPlacesByBookmark(isBookmarked)
    }

    fun getPlacesBySearchQuery(query: String): Flow<List<PlaceEntity>> {
        return dao.getPlacesBySearchQuery(query)
    }

    suspend fun updateBookmark(id: Int, isBookmarked: Boolean) {
        dao.updateBookmark(id, isBookmarked)
    }





}