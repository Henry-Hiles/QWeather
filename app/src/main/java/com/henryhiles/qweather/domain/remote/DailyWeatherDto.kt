package com.henryhiles.qweather.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDataDto(
    @SerialName("time")
    val date: List<String>,
    @SerialName("weathercode")
    val weatherCode: List<Int>,
    val sunrise: List<String>,
    val sunset: List<String>,
    @SerialName("precipitation_probability_max")
    val precipitationProbabilityMax: List<Int?>,
    @SerialName("precipitation_sum")
    val precipitationSum: List<Float>,
    @SerialName("windspeed_10m_max")
    val windSpeedMax: List<Float>,
    @SerialName("temperature_2m_max")
    val temperatureMax: List<Float>,
    @SerialName("temperature_2m_min")
    val temperatureMin: List<Float>,
    @SerialName("apparent_temperature_max")
    val apparentTemperatureMax: List<Float>,
    @SerialName("apparent_temperature_min")
    val apparentTemperatureMin: List<Float>
)
@Serializable
data class DailyWeatherUnitsDto(
    @SerialName("precipitation_probability_max")
    val precipitationProbabilityMax: String,
    @SerialName("precipitation_sum")
    val precipitationSum: String,
    @SerialName("windspeed_10m_max")
    val windSpeedMax: String,
    @SerialName("temperature_2m_max")
    val temperatureMax: String,
    @SerialName("temperature_2m_min")
    val temperatureMin: String,
    @SerialName("apparent_temperature_max")
    val apparentTemperatureMax: String,
    @SerialName("apparent_temperature_min")
    val apparentTemperatureMin: String
)