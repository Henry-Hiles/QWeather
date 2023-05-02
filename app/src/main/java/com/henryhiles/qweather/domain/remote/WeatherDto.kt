package com.henryhiles.qweather.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("hourly")
    val hourlyWeatherData: HourlyWeatherDataDto,

    @SerialName("daily")
    val dailyWeatherData: DailyWeatherDataDto
)