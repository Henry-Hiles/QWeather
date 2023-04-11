package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class HourlyWeatherDataDto(
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperature: List<Double>,
    @field:Json(name = "apparent_temperature")
    val apparentTemperature: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCode: List<Int>,
    @field:Json(name = "precipitation_probability")
    val precipitationProbability: List<Int>,
    @field:Json(name = "windspeed_10m")
    val windSpeed: List<Double>,
)