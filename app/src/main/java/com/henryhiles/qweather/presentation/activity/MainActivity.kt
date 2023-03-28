package com.henryhiles.qweather.presentation.activity

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.henryhiles.qweather.presentation.screen.TodayScreen
import com.henryhiles.qweather.presentation.ui.theme.WeatherAppTheme
import com.henryhiles.qweather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherViewModel = getViewModel<WeatherViewModel>()

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { weatherViewModel.loadWeatherInfo() }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
        setContent {
            WeatherAppTheme {
                Surface {
                    Navigator(TodayScreen()) { navigator ->
                        Scaffold(
                            topBar = { /* ... */ },
                            content = { padding -> Box(modifier = Modifier.padding(padding)) { CurrentScreen() } },
                            bottomBar = { /* ... */ }
                        )
                    }
                }
            }
        }
    }
}