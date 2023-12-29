package com.henryhiles.qweather.domain.repository

import androidx.compose.ui.text.toLowerCase
import com.henryhiles.qweather.domain.mappers.toDailyWeatherData
import com.henryhiles.qweather.domain.mappers.toHourlyWeatherInfo
import com.henryhiles.qweather.domain.remote.WeatherApi
import com.henryhiles.qweather.domain.remote.WeatherDto
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import com.henryhiles.qweather.domain.weather.HourlyWeatherInfo
import com.henryhiles.qweather.presentation.screenmodel.UnitPreferenceManager

class WeatherRepository(private val api: WeatherApi) {
    private suspend fun getWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true,
    ): WeatherDto {
        return if (cache) api.getWeatherData(
            lat,
            long,
            units.tempUnit.name.lowercase(),
            units.windUnit.name.lowercase(),
            units.precipitationUnit.name.lowercase(),
        ) else api.getWeatherDataWithoutCache(
            lat,
            long,
            units.tempUnit.name,
            units.windUnit.name,
            units.precipitationUnit.name,
        )
    }

    suspend fun getDailyWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): Resource<List<DailyWeatherData>> {
        return try {
            Resource.Success(
                with(getWeatherData(lat, long, units, cache)) {
                    dailyWeatherData.toDailyWeatherData(dailyUnits)
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    suspend fun getHourlyWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): Resource<HourlyWeatherInfo> {
        return try {
            Resource.Success(
                getWeatherData(lat, long, units, cache).toHourlyWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}