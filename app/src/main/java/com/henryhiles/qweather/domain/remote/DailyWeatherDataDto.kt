package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class DailyWeatherDataDto(
    @field:Json(name = "time")
    val date: List<String>,
    @field:Json(name = "weathercode")
    val weatherCode: List<Int>,
    @field:Json(name = "precipitation_probability_max")
    val precipitationProbabilityMax: List<Int>,
    @field:Json(name = "precipitation_sum")
    val precipitationSum: List<Float>,
    @field:Json(name = "windspeed_10m_max")
    val windSpeedMax: List<Float>,
    @field:Json(name = "temperature_2m_max")
    val temperatureMax: List<Float>,
    @field:Json(name = "temperature_2m_min")
    val temperatureMin: List<Float>,
    @field:Json(name = "apparent_temperature_max")
    val apparentTemperatureMax: List<Float>,
    @field:Json(name = "apparent_temperature_min")
    val apparentTemperatureMin: List<Float>
)