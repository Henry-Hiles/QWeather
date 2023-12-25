package com.henryhiles.qweather.presentation.components.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.VerticalDivider
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherState
import com.henryhiles.qweather.presentation.screenmodel.LocationPreferenceManager
import org.koin.compose.koinInject

@Composable
fun WeatherToday(state: HourlyWeatherState) {
    val locationPreferenceManager: LocationPreferenceManager = koinInject()

    state.hourlyWeatherInfo?.let {
        Card(
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.today_in, with(locationPreferenceManager) {
                        locations.getOrNull(selectedIndex)?.location?.split(",")?.first()
                            ?: stringResource(id = R.string.unknown)
                    }),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
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
                        } ?: stringResource(id = R.string.unknown)
                    )
                }
            }
        }
    }
}