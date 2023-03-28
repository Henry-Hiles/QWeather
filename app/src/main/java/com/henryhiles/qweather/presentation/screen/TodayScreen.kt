package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.henryhiles.qweather.presentation.components.WeatherCard
import com.henryhiles.qweather.presentation.components.WeatherForecast
import com.henryhiles.qweather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.compose.getViewModel

class TodayScreen : AndroidScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val weatherViewModel = getViewModel<WeatherViewModel>()
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                WeatherCard(state = weatherViewModel.state)
                Spacer(modifier = Modifier.height(16.dp))
                WeatherForecast(state = weatherViewModel.state)
            }
            if (weatherViewModel.state.isLoading) CircularProgressIndicator(
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
            weatherViewModel.state.error?.let {
                AlertDialog(onDismissRequest = {}) {
                    Surface(
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "An error occurred",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            SelectionContainer {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }

                        }
                    }
                }

            }
        }
    }
}