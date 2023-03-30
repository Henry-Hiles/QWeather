package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.tabs.SettingsTab
import com.henryhiles.qweather.presentation.tabs.TodayTab
import com.henryhiles.qweather.presentation.tabs.WeekTab

class MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(tab = TodayTab) { navigator ->
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = navigator.current == TodayTab,
                            onClick = { navigator.current = TodayTab },
                            label = { Text(text = stringResource(id = R.string.tab_today)) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = stringResource(id = R.string.tab_today)
                                )
                            })
                        NavigationBarItem(
                            selected = navigator.current == WeekTab,
                            onClick = { navigator.current = WeekTab },
                            label = { Text(text = "Weekly") },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Weekly"
                                )
                            })
                        NavigationBarItem(
                            selected = navigator.current == SettingsTab,
                            onClick = { navigator.current = SettingsTab },
                            label = { Text(text = "Settings") },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings"
                                )
                            })
                    }
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    CurrentScreen()
                }
            }
        }
    }
}