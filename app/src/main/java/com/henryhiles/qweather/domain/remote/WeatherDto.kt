package com.henryhiles.qweather.domain.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val hourlyWeatherData: HourlyWeatherDataDto,

    @field:Json(name = "daily")
    val dailyWeatherData: DailyWeatherDataDto
)