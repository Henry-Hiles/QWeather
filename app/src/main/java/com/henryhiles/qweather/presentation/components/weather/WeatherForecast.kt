package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherState
import java.time.LocalDateTime

@Composable
fun WeatherForecast(state: HourlyWeatherState, modifier: Modifier = Modifier) {
    state.hourlyWeatherInfo?.weatherDataPerDay?.get(0)?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Today", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            val rowState = rememberLazyListState(LocalDateTime.now().hour)

            LazyRow(state = rowState) {
                items(it) {
                    WeatherHour(
                        data = it,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}