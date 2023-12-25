package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherState
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: HourlyWeatherState,
    modifier: Modifier = Modifier,
    onChangeSelected: (Int) -> Unit
) {
    state.hourlyWeatherInfo?.weatherData?.let {
        val hour = LocalDateTime.now().hour
        LazyRow(modifier = modifier) {
            itemsIndexed(it.subList(hour, it.size)) { index, data ->
                WeatherHour(
                    data = data,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) { onChangeSelected(index) }
            }
        }
    }
}