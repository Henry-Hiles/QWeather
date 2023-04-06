package com.henryhiles.qweather.presentation.tabs

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.screenmodel.DailyWeatherScreenModel
import java.time.format.DateTimeFormatter

object WeekTab : Tab {
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
                            items(data) {
                                val formattedDate = it.date.format(
                                    DateTimeFormatter.ofPattern("E d")
                                )
                                Card(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = it.weatherType.iconRes),
                                            contentDescription = "Image of ${it.weatherType}",
                                            modifier = Modifier.width(48.dp)
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column {
                                            Text(
                                                text = formattedDate
                                            )
                                            Text(text = "Feels like ${it.apparentTemperatureMax}°C")
                                        }

                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            text = "${it.temperatureMax}°C",
                                            style = MaterialTheme.typography.titleLarge,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}