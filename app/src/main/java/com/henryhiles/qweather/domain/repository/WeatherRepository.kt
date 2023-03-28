package com.henryhiles.qweather.domain.repository

import com.henryhiles.qweather.domain.util.Resource
import com.henryhiles.qweather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}