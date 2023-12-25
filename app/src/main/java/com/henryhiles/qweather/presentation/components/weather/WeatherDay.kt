package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Water
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WindPower
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDay(dailyWeatherData: DailyWeatherData) {
    val formattedDate by remember {
        derivedStateOf {
            dailyWeatherData.date.format(
                DateTimeFormatter.ofPattern("E d")
            )
        }
    }

    Card(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = dailyWeatherData.weatherType.iconRes),
                contentDescription = "Image of ${dailyWeatherData.weatherType.weatherDesc}",
                modifier = Modifier.width(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = formattedDate
                )
                Text(text = "Feels like ${dailyWeatherData.apparentTemperatureMax}°C")
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${dailyWeatherData.temperatureMax}°C",
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WeatherDataDisplay(
                value = dailyWeatherData.precipitationProbabilityMax,
                unit = "%",
                icon = Icons.Outlined.WaterDrop,
                description = "Chance of rain"
            )
            Spacer(modifier = Modifier.width(16.dp))
            WeatherDataDisplay(
                value = dailyWeatherData.windSpeedMax,
                unit = "mm",
                icon = Icons.Outlined.Water,
                description = "Precipitation Amount"
            )
            Spacer(modifier = Modifier.width(16.dp))
            WeatherDataDisplay(
                value = dailyWeatherData.windSpeedMax,
                unit = "km/h",
                icon = Icons.Outlined.WindPower,
                description = "Wind Speed"
            )
        }
    }
}