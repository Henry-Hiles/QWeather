package com.henryhiles.qweather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.henryhiles.qweather.presentation.screen.MainScreen
import com.henryhiles.qweather.presentation.ui.theme.WeatherAppTheme

class QWeatherActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface {
                    Navigator(screen = MainScreen()) {
                        SlideTransition(it)
                    }
                }
            }
        }
    }
}