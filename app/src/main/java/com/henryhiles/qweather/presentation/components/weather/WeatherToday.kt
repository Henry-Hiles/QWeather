package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.VerticalDivider
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherState

@Composable
fun WeatherToday(state: HourlyWeatherState) {
    state.hourlyWeatherInfo?.let {
        Row(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.weather_high, it.highTemperature),
            )
            VerticalDivider(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = stringResource(id = R.string.weather_low, it.lowTemperature)
            )
            VerticalDivider(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = it.precipitationProbability?.let {
                    stringResource(
                        id = R.string.weather_precipitation,
                        it
                    )
                } ?: stringResource(
                    id = R.string.unknown
                )
            )
        }
    }
}