package com.henryhiles.qweather.presentation.screenmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.henryhiles.qweather.domain.location.LocationTracker
import com.henryhiles.qweather.domain.manager.BasePreferenceManager
import com.henryhiles.qweather.domain.remote.GeocodingLocationDto
import com.henryhiles.qweather.domain.repository.GeocodingRepository
import com.henryhiles.qweather.domain.util.Resource
import kotlinx.coroutines.launch

data class LocationPickerState(
    val locations: List<GeocodingLocationDto>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

class LocationPreferenceManager(context: Context) :
    BasePreferenceManager(context.getSharedPreferences("location", Context.MODE_PRIVATE)) {
    var latitude by floatPreference("lat", 0f)
    var longitude by floatPreference("long", 0f)
    var location by stringPreference("string")
}

class LocationPickerScreenModel(
    val prefs: LocationPreferenceManager,
    private val repository: GeocodingRepository,
    private val locationTracker: LocationTracker,
    private val context: Context
) : ScreenModel {
    var state by mutableStateOf(LocationPickerState())
        private set

    fun loadGeolocationInfo(location: String) {
        coroutineScope.launch {
            state = state.copy(isLoading = true, error = null)

            state = when (val result =
                repository.getGeocodingData(
                    location = location
                )) {
                is Resource.Success -> {
                    state.copy(
                        locations = result.data,
                        isLoading = false,
                        error = null,
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        locations = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}