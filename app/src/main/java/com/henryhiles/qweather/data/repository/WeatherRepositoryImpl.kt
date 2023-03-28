package com.henryhiles.qweather.data.repository

import com.henryhiles.qweather.data.mappers.toWeatherInfo
import com.henryhiles.qweather.data.remote.WeatherApi
import com.henryhiles.qweather.domain.repository.WeatherRepository
import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.WeatherInfo

class WeatherRepositoryImpl constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(data = api.getWeatherData(lat = lat, long = long).toWeatherInfo())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}