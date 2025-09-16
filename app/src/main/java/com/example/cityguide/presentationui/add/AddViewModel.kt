package com.example.cityguide.presentationui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityguide.data.local.res.PlaceEntity
import com.example.cityguide.data.repository.PlaceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val repository: PlaceRepo
) : ViewModel() {

    private val _state = MutableStateFlow(AddScreenState())
    val state: StateFlow<AddScreenState> = _state

    fun onValueChange(
        placeName: String = _state.value.placeName,
        category: String = _state.value.category,
        description: String = _state.value.description,
        address: String = _state.value.address,
        rating: String = _state.value.rating,
        image: ByteArray? = _state.value.image
    ) {
        _state.value = _state.value.copy(
            placeName = placeName,
            category = category,
            description = description,
            address = address,
            image = image,
            rating = rating
        )
    }

    fun addPlace(onSuccess: () -> Unit) {
        val current = _state.value

        if (current.placeName.isBlank() || current.category.isBlank() || current.description.isBlank() || current.address.isBlank()) {
            _state.value = current.copy(error = "Please fill all fields")
            return
        }

        _state.value = current.copy(isSaving = true, error = null)

        val ratingDouble = current.rating.toDoubleOrNull() ?: 0.0
        val place = PlaceEntity(
            placeName = current.placeName,
            category = current.category,
            description = current.description,
            address = current.address,
            rating = ratingDouble,
            image = current.image
        )

        viewModelScope.launch {
            try {
                repository.insertPlace(place)
                _state.value = AddScreenState()
                onSuccess()
            } catch (e: Exception) {
                _state.value = current.copy(isSaving = false, error = e.message)
            }
        }
    }
}
