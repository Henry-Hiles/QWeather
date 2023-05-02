package com.henryhiles.qweather.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDataDto(
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature: List<Float>,
    @SerialName("apparent_temperature")
    val apparentTemperature: List<Float>,
    @SerialName("weathercode")
    val weatherCode: List<Int>,
    @SerialName("precipitation_probability")
    val precipitationProbability: List<Int?>,
    @SerialName("windspeed_10m")
    val windSpeed: List<Float>,
)