package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WindPower
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.henryhiles.qweather.domain.util.getIcon
import com.henryhiles.qweather.domain.weather.DailyWeatherData
import com.henryhiles.qweather.domain.weather.HourlyWeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(hour: HourlyWeatherData, dailyData: DailyWeatherData, modifier: Modifier = Modifier) {
    val formattedTime = remember(hour) {
        hour.time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = formattedTime,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = getIcon(hour, dailyData)),
                contentDescription = "Image of ${hour.weatherType.weatherDesc}",
                modifier = Modifier.height(140.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "${hour.temperature}°C", fontSize = 50.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "${hour.weatherType.weatherDesc} - Feels like ${hour.apparentTemperature}°C", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDataDisplay(
                    value = hour.precipitationProbability,
                    unit = "%",
                    icon = Icons.Outlined.WaterDrop,
                    description = "Chance of precipitation"
                )
                WeatherDataDisplay(
                    value = hour.windSpeed,
                    unit = "km/h",
                    icon = Icons.Outlined.WindPower,
                    description = "Wind Speed",
                )
            }
        }
    }
}