package com.henryhiles.qweather.presentation.screenmodel

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.henryhiles.qweather.domain.location.LocationTracker
import com.henryhiles.qweather.domain.repository.WeatherRepository
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import kotlinx.coroutines.launch

data class DailyWeatherState(
    val dailyWeatherData: List<DailyWeatherData>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class DailyWeatherScreenModel constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
) : ScreenModel {
    var state by mutableStateOf(DailyWeatherState())
        private set
    private var currentLocation: Location? = null

    fun loadWeatherInfo(cache: Boolean = true) {
        coroutineScope.launch {
            state = state.copy(isLoading = true, error = null)
            currentLocation = locationTracker.getCurrentLocation()
            currentLocation?.let { location ->
                state = when (val result =
                    repository.getDailyWeatherData(
                        lat = location.latitude,
                        long = location.longitude,
                        cache = cache
                    )) {
                    is Resource.Success -> {
                        state.copy(
                            dailyWeatherData = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state.copy(
                            dailyWeatherData = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}