package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.domain.weather.HourlyWeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHour(
    data: HourlyWeatherData,
    modifier: Modifier = Modifier,
    onChangeSelected: () -> Unit
) {
    data.let {
        val formattedTime by remember {
            derivedStateOf { it.time.format(DateTimeFormatter.ofPattern("HH:mm")) }
        }
        Card(modifier = modifier.clickable {
            onChangeSelected()
        }) {
            Column(
                modifier = Modifier
                    .height(128.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(text = formattedTime)
                Image(
                    painter = painterResource(id = it.weatherType.iconRes),
                    contentDescription = "Image of ${it.weatherType.weatherDesc}",
                    modifier = Modifier.width(40.dp)
                )
                Text(text = "${it.temperature}°C")
            }
        }
    }

}