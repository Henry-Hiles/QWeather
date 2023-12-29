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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.R
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

    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = dailyWeatherData.weatherType.iconRes),
                contentDescription = dailyWeatherData.weatherType.weatherDesc,
                modifier = Modifier.width(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = formattedDate,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(
                        id = R.string.feels_like,
                        dailyWeatherData.apparentTemperatureMax,
                        dailyWeatherData.units.apparentTemperatureMax
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${dailyWeatherData.temperatureMax}${dailyWeatherData.units.temperatureMax}",
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
                unit = dailyWeatherData.units.precipitationProbabilityMax,
                icon = Icons.Outlined.WaterDrop,
                description = stringResource(id = R.string.precipitation_probability)
            )
            WeatherDataDisplay(
                value = dailyWeatherData.precipitationSum,
                unit = dailyWeatherData.units.precipitationSum,
                icon = Icons.Outlined.Water,
                description = stringResource(id = R.string.precipitation_amount)
            )
            WeatherDataDisplay(
                value = dailyWeatherData.windSpeedMax,
                unit = dailyWeatherData.units.windSpeedMax,
                icon = Icons.Outlined.WindPower,
                description = stringResource(id = R.string.wind_speed)
            )
        }
    }
}