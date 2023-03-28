package com.henryhiles.qweather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHour(data: WeatherData, modifier: Modifier = Modifier) {
    data.let {
        val formattedTime = remember(it) {
            it.time.format(DateTimeFormatter.ofPattern("HH:mm"))
        }

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formattedTime)
            Image(
                painter = painterResource(id = it.weatherType.iconRes),
                contentDescription = "Image of ${data.weatherType.weatherDesc}",
                modifier = Modifier.width(40.dp)
            )
            Text(text = "${it.temperatureCelsius}°C")
        }
    }

}