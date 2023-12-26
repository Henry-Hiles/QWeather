package com.henryhiles.qweather.presentation.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.henryhiles.qweather.presentation.components.weather.WeatherDay
import com.henryhiles.qweather.presentation.components.weather.WeatherToday
import com.henryhiles.qweather.presentation.screenmodel.DailyWeatherScreenModel
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherScreenModel

object WeekTab : NavigationTab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tab_weekly)
            val icon = rememberVectorPainter(Icons.Default.DateRange)

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
        val hourlyWeatherViewModel = getScreenModel<HourlyWeatherScreenModel>()
        val dailyWeatherViewModel = getScreenModel<DailyWeatherScreenModel>()

        LaunchedEffect(key1 = dailyWeatherViewModel.locationPreferenceManager.selectedIndex) {
            dailyWeatherViewModel.loadWeatherInfo()
            hourlyWeatherViewModel.loadWeatherInfo()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                dailyWeatherViewModel.state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                dailyWeatherViewModel.state.error != null -> {
                    AlertDialog(
                        onDismissRequest = {},
                        confirmButton = {},
                        title = { Text(text = stringResource(R.string.error)) },
                        text = {
                            SelectionContainer {
                                Text(
                                    text = dailyWeatherViewModel.state.error!!,
                                )
                            }
                        },
                    )
                }

                else -> {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        dailyWeatherViewModel.state.dailyWeatherData?.let { data ->
                            item { WeatherToday(state = hourlyWeatherViewModel.state) }
                            items(data) {
                                Spacer(modifier = Modifier.height(16.dp))
                                WeatherDay(dailyWeatherData = it)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    override fun Actions() {
        val hourlyWeatherViewModel = getScreenModel<HourlyWeatherScreenModel>()
        val dailyWeatherViewModel = getScreenModel<DailyWeatherScreenModel>()

        IconButton(onClick = {
            hourlyWeatherViewModel.loadWeatherInfo(cache = false)
            dailyWeatherViewModel.loadWeatherInfo(cache = false)
        }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.action_reload)
            )
        }
    }
}