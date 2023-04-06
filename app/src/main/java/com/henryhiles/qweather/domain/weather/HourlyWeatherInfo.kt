package com.henryhiles.qweather.domain.weather

data class HourlyWeatherInfo(
    val weatherDataPerDay: Map<Int, List<HourlyWeatherData>>,
    val currentWeatherData: HourlyWeatherData?
)