package com.henryhiles.qweather.domain.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val DAILY =
    "daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min"
const val HOURLY =
    "hourly=temperature_2m,apparent_temperature,precipitation_probability,weathercode,windspeed_10m"
const val TIMEZONE = "timezone=auto"
const val FORECAST_DAYS = "forecast_days=14"
const val URL = "v1/forecast?$HOURLY&$DAILY&$TIMEZONE&$FORECAST_DAYS"

interface WeatherApi {
    @GET(URL)
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
    ): WeatherDto

    @Headers("Cache-Control: no-cache")
    @GET(URL)
    suspend fun getWeatherDataWithoutCache(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
    ): WeatherDto
}