package com.henryhiles.qweather.domain.mappers

import com.henryhiles.qweather.domain.remote.WeatherDataDto
import com.henryhiles.qweather.domain.remote.WeatherDto
import com.henryhiles.qweather.domain.weather.WeatherData
import com.henryhiles.qweather.domain.weather.WeatherInfo
import com.henryhiles.qweather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(val index: Int, val data: WeatherData)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        IndexedWeatherData(
            index = index, data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperatures[index].toInt(),
                pressure = pressures[index],
                windSpeed = windSpeeds[index],
                humidity = humidities[index],
                weatherType = WeatherType.fromWMO(weatherCodes[index])
            )
        )
    }.groupBy { it.index / 24 }.mapValues { entry -> entry.value.map { it.data } }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        it.time.hour == now.hour
    }
    return WeatherInfo(weatherDataPerDay = weatherDataMap, currentWeatherData = currentWeatherData)
}