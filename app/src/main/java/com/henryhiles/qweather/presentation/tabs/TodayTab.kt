package com.henryhiles.qweather.presentation.tabs

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.weather.WeatherCard
import com.henryhiles.qweather.presentation.components.weather.WeatherForecast
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherScreenModel

object TodayTab : Tab {
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

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
    @Composable
    override fun Content() {
        val weatherViewModel = getScreenModel<HourlyWeatherScreenModel>()

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
                        title = { Text(text = "An error occurred") }, text = {
                            SelectionContainer {
                                Text(
                                    text = weatherViewModel.state.error!!,
                                )
                            }
                        })
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        WeatherCard(state = weatherViewModel.state)
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = weatherViewModel.state)
                    }
                }
            }
        }
    }
}