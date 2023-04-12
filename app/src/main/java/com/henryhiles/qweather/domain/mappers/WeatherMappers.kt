package com.henryhiles.qweather.domain.mappers

import com.henryhiles.qweather.domain.remote.DailyWeatherDataDto
import com.henryhiles.qweather.domain.remote.HourlyWeatherDataDto
import com.henryhiles.qweather.domain.remote.WeatherDto
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import com.henryhiles.qweather.domain.weather.HourlyWeatherData
import com.henryhiles.qweather.domain.weather.HourlyWeatherInfo
import com.henryhiles.qweather.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun HourlyWeatherDataDto.toHourlyWeatherDataMap(): List<HourlyWeatherData> {
    return time.subList(0, 24).mapIndexed { index, time ->
        HourlyWeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperature = temperature[index].roundToInt(),
            apparentTemperature = apparentTemperature[index].roundToInt(),
            windSpeed = windSpeed[index].roundToInt(),
            precipitationProbability = precipitationProbability.getOrNull(index),
            weatherType = WeatherType.fromWMO(weatherCode[index])
        )
    }
}

fun DailyWeatherDataDto.toDailyWeatherDataMap(): List<DailyWeatherData> {
    return date.mapIndexed { index, date ->
        DailyWeatherData(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
            weatherType = WeatherType.fromWMO(weatherCode[index]),
            apparentTemperatureMax = apparentTemperatureMax[index].roundToInt(),
            apparentTemperatureMin = apparentTemperatureMin[index].roundToInt(),
            temperatureMax = temperatureMax[index].roundToInt(),
            temperatureMin = temperatureMin[index].roundToInt(),
            precipitationProbabilityMax = precipitationProbabilityMax.getOrNull(index),
            windSpeedMax = windSpeedMax[index].roundToInt()
        )
    }
}

fun WeatherDto.toHourlyWeatherInfo(): HourlyWeatherInfo {
    val weatherDataMap = hourlyWeatherData.toHourlyWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap.find {
        it.time.hour == now.hour
    }
    return HourlyWeatherInfo(
        weatherData = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}