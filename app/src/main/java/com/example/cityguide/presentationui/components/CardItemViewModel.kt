package com.example.cityguide.presentationui.components

import kotlinx.coroutines.flow.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityguide.data.local.res.PlaceEntity
import com.example.cityguide.data.repository.PlaceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardItemViewModel @Inject constructor(
    private val repo: PlaceRepo
) : ViewModel() {

    fun getPlaceById(id: Int): Flow<PlaceEntity?> {
        // You can add getPlaceById in Dao/Repo
        return repo.getAllPlaces().map { list -> list.find { it.id == id } }
    }

    fun toggleBookmark(place: PlaceEntity) {
        viewModelScope.launch {
            repo.updateBookmark(place.id, !place.isBookmarked)
        }
    }
}
