package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherState
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(state: HourlyWeatherState, modifier: Modifier = Modifier) {
    state.hourlyWeatherInfo?.currentWeatherData?.let {
        val formattedTime = remember(it) {
            it.time.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today $formattedTime",
                    modifier = Modifier.align(Alignment.End), color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = it.weatherType.iconRes),
                    contentDescription = "Image of ${it.weatherType.weatherDesc}",
                    modifier = Modifier.width(200.dp)
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
                        icon = Icons.Default.Thermostat,
                        description = "Feels like",
                    )
                    WeatherDataDisplay(
                        value = it.precipitationProbability,
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        description = "Chance of precipitation"
                    )
                    WeatherDataDisplay(
                        value = it.windSpeed,
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        description = "Wind Speed",
                    )
                }
            }
        }
    }
}