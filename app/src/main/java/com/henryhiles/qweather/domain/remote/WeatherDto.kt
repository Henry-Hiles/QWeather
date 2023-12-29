package com.henryhiles.qweather.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("hourly")
    val hourlyWeatherData: HourlyWeatherDataDto,

    @SerialName("hourly_units")
    val hourlyUnits: HourlyWeatherUnitsDto,

    @SerialName("daily")
    val dailyWeatherData: DailyWeatherDataDto,

    @SerialName("daily_units")
    val dailyUnits: DailyWeatherUnitsDto,
)