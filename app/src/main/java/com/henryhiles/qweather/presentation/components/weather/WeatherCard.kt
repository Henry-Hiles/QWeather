package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WindPower
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.henryhiles.qweather.domain.weather.HourlyWeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(hour: HourlyWeatherData?, modifier: Modifier = Modifier) {
    hour?.let {
        val formattedTime = remember(it) {
            it.time.format(DateTimeFormatter.ofPattern("HH:mm"))
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
                        text = "Today $formattedTime",
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = it.icon),
                    contentDescription = "Image of ${it.weatherType.weatherDesc}",
                    modifier = Modifier.height(140.dp),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "${it.temperature}°C", fontSize = 50.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.weatherType.weatherDesc, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = it.apparentTemperature,
                        unit = "°C",
                        icon = Icons.Outlined.Thermostat,
                        description = "Feels like",
                    )
                    WeatherDataDisplay(
                        value = it.precipitationProbability,
                        unit = "%",
                        icon = Icons.Outlined.WaterDrop,
                        description = "Chance of precipitation"
                    )
                    WeatherDataDisplay(
                        value = it.windSpeed,
                        unit = "km/h",
                        icon = Icons.Outlined.WindPower,
                        description = "Wind Speed",
                    )
                }
            }
        }
    }
}