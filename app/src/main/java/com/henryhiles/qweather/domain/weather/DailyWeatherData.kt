package com.henryhiles.qweather.domain.weather

import java.time.LocalDate
import java.time.LocalDateTime

data class DailyWeatherData(
    val date: LocalDate,
    val weatherType: WeatherType,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val apparentTemperatureMax: Int,
    val apparentTemperatureMin: Int,
    val precipitationProbabilityMax: Int?,
    val windSpeedMax: Int
)