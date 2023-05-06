package com.henryhiles.qweather.presentation.screenmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.henryhiles.qweather.domain.repository.WeatherRepository
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import kotlinx.coroutines.launch

data class DailyWeatherState(
    val dailyWeatherData: List<DailyWeatherData>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val expanded: Int? = null
)

class DailyWeatherScreenModel(
    private val repository: WeatherRepository,
    val locationPreferenceManager: LocationPreferenceManager
) : ScreenModel {
    var state by mutableStateOf(DailyWeatherState())
        private set

    fun loadWeatherInfo(cache: Boolean = true) {
        val location = locationPreferenceManager.locations[locationPreferenceManager.selectedIndex]
        coroutineScope.launch {
            state = state.copy(isLoading = true, error = null)
            state = when (val result = repository.getDailyWeatherData(
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
        }
    }

    fun setExpanded(index: Int?) {
        state = state.copy(expanded = index)
    }
}