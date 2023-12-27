package com.henryhiles.qweather.domain.util

import com.henryhiles.qweather.domain.weather.DailyWeatherData
import com.henryhiles.qweather.domain.weather.HourlyWeatherData

fun getIcon(
    data: HourlyWeatherData,
    dailyData: DailyWeatherData,
): Int {
    return if (data.time.isAfter(dailyData.sunrise) && data.time.isBefore(
            dailyData.sunset
        )
    ) data.weatherType.iconRes else data.weatherType.nightIconRes
}