package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherState
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: HourlyWeatherState,
    modifier: Modifier = Modifier,
    onChangeSelected: (Int) -> Unit
) {
    state.hourlyWeatherInfo?.weatherData?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Today", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            val rowState = rememberLazyListState(LocalDateTime.now().hour)

            LazyRow(state = rowState) {
                itemsIndexed(it) { index, data ->
                    WeatherHour(
                        data = data,
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        onChangeSelected = { onChangeSelected(index) }
                    )
                }
            }
        }
    }
}