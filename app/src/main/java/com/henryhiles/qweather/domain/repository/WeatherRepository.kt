package com.henryhiles.qweather.domain.repository

import com.henryhiles.qweather.domain.mappers.toWeatherInfo
import com.henryhiles.qweather.domain.remote.WeatherApi
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.WeatherInfo

class WeatherRepository constructor(private val api: WeatherApi) {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(data = api.getWeatherData(lat = lat, long = long).toWeatherInfo())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}