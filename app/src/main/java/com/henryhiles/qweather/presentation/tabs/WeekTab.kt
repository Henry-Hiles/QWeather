package com.henryhiles.qweather.presentation.tabs

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.henryhiles.qweather.R
import com.henryhiles.qweather.domain.util.NavigationTab
import com.henryhiles.qweather.presentation.components.weather.WeatherDay
import com.henryhiles.qweather.presentation.screenmodel.DailyWeatherScreenModel

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

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    override fun Content() {
        val weatherViewModel = getScreenModel<DailyWeatherScreenModel>()

        val permissionsState = rememberPermissionState(
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) {
            weatherViewModel.loadWeatherInfo()
        }

        LaunchedEffect(key1 = true) {
            permissionsState.launchPermissionRequest()
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
                        confirmButton = {},
                        title = { Text(text = "An error occurred") },
                        text = {
                            SelectionContainer {
                                Text(
                                    text = weatherViewModel.state.error!!,
                                )
                            }
                        })
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        weatherViewModel.state.dailyWeatherData?.let { data ->
                            itemsIndexed(data) { index, dailyData ->
                                val expanded = weatherViewModel.state.expanded == index
                                WeatherDay(
                                    dailyWeatherData = dailyData,
                                    expanded = expanded,
                                    onExpand = { weatherViewModel.setExpanded(if (expanded) null else index) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    override fun Actions() {
        val viewModel: DailyWeatherScreenModel = getScreenModel()

        IconButton(onClick = { viewModel.loadWeatherInfo(cache = false) }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.action_reload)
            )
        }
    }
}