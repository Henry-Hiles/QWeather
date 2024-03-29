package com.henryhiles.qweather.presentation.tabs

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
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
import com.henryhiles.qweather.presentation.screenmodel.DailyWeatherScreenModel
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
        val dailyWeatherViewModel = getScreenModel<DailyWeatherScreenModel>()

        LaunchedEffect(key1 = weatherViewModel.locationPreferenceManager.selectedIndex) {
            weatherViewModel.loadWeatherInfo()
            dailyWeatherViewModel.loadWeatherInfo()
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
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        dailyWeatherViewModel.state.dailyWeatherData?.get(0)
                            ?.let { dailyWeatherData ->
                                WeatherToday(data = dailyWeatherData)
                                (weatherViewModel.state.selected?.let {
                                    weatherViewModel.state.hourlyWeatherInfo?.weatherData?.get(it)
                                } ?: weatherViewModel.state.hourlyWeatherInfo?.currentWeatherData)
                                    ?.let {
                                        WeatherCard(
                                            hour = it,
                                            dailyData = dailyWeatherData
                                        )
                                    }

                                WeatherForecast(
                                    state = weatherViewModel.state,
                                    dailyData = dailyWeatherData
                                ) { weatherViewModel.setSelected(it) }
                            }
                    }
                }
            }
        }
    }

    @Composable
    override fun Actions() {
        val weatherViewModel = getScreenModel<HourlyWeatherScreenModel>()
        val dailyWeatherViewModel = getScreenModel<DailyWeatherScreenModel>()


        IconButton(onClick = {
            weatherViewModel.loadWeatherInfo(cache = false)
            dailyWeatherViewModel.loadWeatherInfo(cache = false)
        }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.action_reload)
            )
        }
    }
}