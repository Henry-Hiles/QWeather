package com.henryhiles.qweather.domain.weather

data class HourlyWeatherInfo(
    val weatherData: List<HourlyWeatherData>,
    val currentWeatherData: HourlyWeatherData?
)