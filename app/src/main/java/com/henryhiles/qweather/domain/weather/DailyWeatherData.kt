package com.henryhiles.qweather.domain.weather

import java.time.LocalDate

data class DailyWeatherData(
    val date: LocalDate,
    val weatherType: WeatherType,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val apparentTemperatureMax: Int,
    val apparentTemperatureMin: Int,
)