package com.example.cityguide.presentationui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityguide.data.local.res.PlaceEntity
import com.example.cityguide.data.repository.PlaceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: PlaceRepo
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    init {
        getAllPlaces()
    }

    private fun getAllPlaces() {
        viewModelScope.launch {
            _state.value = HomeScreenState(isLoading = true)
            try {
                repository.getAllPlaces().collectLatest { placesList ->
                    _state.value = HomeScreenState(places = placesList, isLoading = false)
                }
            } catch (e: Exception) {
                _state.value = HomeScreenState(error = e.message, isLoading = false)
            }
        }
    }

    fun deletePlace(place: PlaceEntity) {
        viewModelScope.launch {
           try{
               repository.deletePlace(place)
           }
           catch(e: Exception){
               _state.value = _state.value.copy(
                   error = e.localizedMessage ?: "Unexpected error"
               )
           }
        }

    }
}
