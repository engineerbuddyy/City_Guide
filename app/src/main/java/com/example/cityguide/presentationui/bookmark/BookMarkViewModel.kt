package com.example.cityguide.presentationui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityguide.data.local.res.PlaceEntity
import com.example.cityguide.data.repository.PlaceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: PlaceRepo
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkScreenState())
    val state: StateFlow<BookmarkScreenState> = _state

    init {
        getBookmarkedPlaces()
    }

    private fun getBookmarkedPlaces() {
        viewModelScope.launch {
            _state.value = BookmarkScreenState(isLoading = true)
            try {
                repository.getPlacesByBookmark(true).collectLatest { list ->
                    _state.value = BookmarkScreenState(places = list, isLoading = false)
                }
            } catch (e: Exception) {
                _state.value = BookmarkScreenState(error = e.message, isLoading = false)
            }
        }
    }
}
