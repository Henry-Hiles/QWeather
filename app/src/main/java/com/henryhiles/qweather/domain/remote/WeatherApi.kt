package com.henryhiles.qweather.domain.remote

import com.henryhiles.qweather.presentation.screenmodel.PrecipitationUnit
import com.henryhiles.qweather.presentation.screenmodel.TempUnit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val DAILY =
    "daily=weathercode,sunrise,sunset,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,precipitation_sum,precipitation_probability_max,windspeed_10m_max"
const val HOURLY =
    "hourly=temperature_2m,apparent_temperature,precipitation_probability,weathercode,windspeed_10m"
const val TIMEZONE = "timezone=auto"
const val FORECAST_DAYS = "forecast_days=14"
const val URL = "v1/forecast?$HOURLY&$DAILY&$TIMEZONE&$FORECAST_DAYS"

interface WeatherApi {
    @GET(URL)
    suspend fun getWeatherData(
        @Query("latitude") lat: Float,
        @Query("longitude") long: Float,
        @Query("temperature_unit") tempUnit:  String,
        @Query("wind_speed_unit") windUnit: String,
        @Query("precipitation_unit") precipitationUnit: String,
    ): WeatherDto

    @Headers("Cache-Control: no-cache")
    @GET(URL)
    suspend fun getWeatherDataWithoutCache(
        @Query("latitude") lat: Float,
        @Query("longitude") long: Float,
        @Query("temperature_unit") tempUnit:  String,
        @Query("wind_speed_unit") windUnit: String,
        @Query("precipitation_unit") precipitationUnit: String,
    ): WeatherDto
}