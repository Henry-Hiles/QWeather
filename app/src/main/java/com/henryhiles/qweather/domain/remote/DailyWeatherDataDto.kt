package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class DailyWeatherDataDto(
    @field:Json(name = "time")
    val dates: List<String>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "temperature_2m_max")
    val temperaturesMax: List<Double>,
    @field:Json(name = "temperature_2m_min")
    val temperaturesMin: List<Double>,
    @field:Json(name = "apparent_temperature_max")
    val apparentTemperaturesMax: List<Double>,
    @field:Json(name = "apparent_temperature_min")
    val apparentTemperaturesMin: List<Double>
)