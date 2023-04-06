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

private data class IndexedHourlyWeatherData(val index: Int, val data: HourlyWeatherData)

fun HourlyWeatherDataDto.toHourlyWeatherDataMap(): Map<Int, List<HourlyWeatherData>> {
    return times.mapIndexed { index, time ->
        IndexedHourlyWeatherData(
            index = index,
            data = HourlyWeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperatures[index].roundToInt(),
                apparentTemperature = apparentTemperatures[index].roundToInt(),
                windSpeed = windSpeeds[index].roundToInt(),
                precipitationProbability = precipitationProbabilities[index],
                weatherType = WeatherType.fromWMO(weatherCodes[index])
            )
        )
    }.groupBy { it.index / 24 }.mapValues { entry -> entry.value.map { it.data } }
}

fun DailyWeatherDataDto.toDailyWeatherDataMap(): List<DailyWeatherData> {
    return dates.mapIndexed { index, date ->
        DailyWeatherData(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
            weatherType = WeatherType.fromWMO(weatherCodes[index]),
            apparentTemperatureMax = apparentTemperaturesMax[index].roundToInt(),
            apparentTemperatureMin = apparentTemperaturesMin[index].roundToInt(),
            temperatureMax = temperaturesMax[index].roundToInt(),
            temperatureMin = temperaturesMin[index].roundToInt()
        )
    }
}

fun WeatherDto.toHourlyWeatherInfo(): HourlyWeatherInfo {
    val weatherDataMap = hourlyWeatherData.toHourlyWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        it.time.hour == now.hour
    }
    return HourlyWeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}