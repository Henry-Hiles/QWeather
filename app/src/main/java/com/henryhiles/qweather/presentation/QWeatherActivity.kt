package com.henryhiles.qweather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.henryhiles.qweather.presentation.screen.MainScreen
import com.henryhiles.qweather.presentation.screenmodel.AppearancePreferenceManager
import com.henryhiles.qweather.presentation.screenmodel.Theme
import com.henryhiles.qweather.presentation.ui.theme.WeatherAppTheme
import org.koin.android.ext.android.inject

class QWeatherActivity : ComponentActivity() {
    private val prefs: AppearancePreferenceManager by inject()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = when (prefs.theme) {
                Theme.SYSTEM -> isSystemInDarkTheme()
                Theme.LIGHT -> false
                Theme.DARK -> true
            }

            WeatherAppTheme(darkTheme = isDark, monet = prefs.monet) {
                Surface {
                    Navigator(screen = MainScreen()) {
                        SlideTransition(it)
                    }
                }
            }
        }
    }
}