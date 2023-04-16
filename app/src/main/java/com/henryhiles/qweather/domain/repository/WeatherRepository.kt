package com.henryhiles.qweather.domain.repository

import com.henryhiles.qweather.domain.mappers.toDailyWeatherDataMap
import com.henryhiles.qweather.domain.mappers.toHourlyWeatherInfo
import com.henryhiles.qweather.domain.remote.WeatherApi
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import com.henryhiles.qweather.domain.weather.HourlyWeatherInfo

class WeatherRepository(private val api: WeatherApi) {
    suspend fun getHourlyWeatherData(
        lat: Float,
        long: Float,
        cache: Boolean = true
    ): Resource<HourlyWeatherInfo> {
        return try {
            Resource.Success(
                data = (
                        if (cache) api.getWeatherData(
                            lat = lat,
                            long = long
                        ) else api.getWeatherDataWithoutCache(
                            lat = lat,
                            long = long
                        )).toHourlyWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    suspend fun getDailyWeatherData(
        lat: Float,
        long: Float,
        cache: Boolean = true
    ): Resource<List<DailyWeatherData>> {
        return try {
            Resource.Success(
                (if (cache) api.getWeatherData(
                    lat = lat,
                    long = long
                ) else api.getWeatherDataWithoutCache(
                    lat = lat,
                    long = long
                )).dailyWeatherData.toDailyWeatherDataMap()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}