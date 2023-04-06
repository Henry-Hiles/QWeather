package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.henryhiles.qweather.domain.util.NavigationTab
import com.henryhiles.qweather.presentation.components.navigation.BottomBar
import com.henryhiles.qweather.presentation.components.navigation.SmallToolbar
import com.henryhiles.qweather.presentation.tabs.TodayTab

class MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(tab = TodayTab) {
            Scaffold(
                topBar = {
                    SmallToolbar(
                        title = { Text(text = "QWeather") },
                        actions = {
                            (it.current as? NavigationTab)?.Actions()
                        }
                    )
                },
                bottomBar = {
                    BottomBar(navigator = it)
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    CurrentScreen()
                }
            }
        }
    }
}