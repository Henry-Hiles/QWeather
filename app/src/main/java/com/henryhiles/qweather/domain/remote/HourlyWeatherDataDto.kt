package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class HourlyWeatherDataDto(
    @field:Json(name = "time")
    val times: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "apparent_temperature")
    val apparentTemperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "precipitation_probability")
    val precipitationProbabilities: List<Int>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
)