package com.henryhiles.qweather.domain.weather

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class HourlyWeatherData(
    val time: LocalDateTime,
    val temperature: Int,
    val apparentTemperature: Int,
    val weatherType: WeatherType,
    val precipitationProbability: Int?,
    val windSpeed: Int,
    @DrawableRes val icon: Int = if(time.hour < 8 || time.hour >= 19) weatherType.nightIconRes else weatherType.iconRes
)