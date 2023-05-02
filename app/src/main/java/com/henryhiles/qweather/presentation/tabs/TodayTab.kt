package com.henryhiles.qweather.presentation.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.henryhiles.qweather.R
import com.henryhiles.qweather.domain.util.NavigationTab
import com.henryhiles.qweather.presentation.components.weather.WeatherCard
import com.henryhiles.qweather.presentation.components.weather.WeatherForecast
import com.henryhiles.qweather.presentation.components.weather.WeatherToday
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherScreenModel

object TodayTab : NavigationTab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tab_today)
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val weatherViewModel = getScreenModel<HourlyWeatherScreenModel>()

        LaunchedEffect(key1 = false) {
            weatherViewModel.loadWeatherInfo()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                weatherViewModel.state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
                weatherViewModel.state.error != null -> {
                    AlertDialog(
                        onDismissRequest = {},
                        confirmButton = {
                            TextButton(onClick = { weatherViewModel.loadWeatherInfo() }) {
                                Text(text = stringResource(id = R.string.action_try_again))
                            }
                        },
                        title = { Text(text = stringResource(id = R.string.error)) },
                        text = {
                            SelectionContainer {
                                Text(
                                    text = weatherViewModel.state.error!!,
                                )
                            }
                        },
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        WeatherCard(
                            hour = weatherViewModel.state.selected?.let {
                                weatherViewModel.state.hourlyWeatherInfo?.weatherData?.get(it)
                            } ?: weatherViewModel.state.hourlyWeatherInfo?.currentWeatherData,
                            location = weatherViewModel.location.location
                        )
                        WeatherToday(state = weatherViewModel.state)
                        WeatherForecast(
                            state = weatherViewModel.state
                        ) { weatherViewModel.setSelected(it) }
                    }
                }
            }
        }
    }

    @Composable
    override fun Actions() {
        val viewModel: HourlyWeatherScreenModel = getScreenModel()

        IconButton(onClick = { viewModel.loadWeatherInfo(cache = false) }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.action_reload)
            )
        }
    }
}