package com.henryhiles.qweather.presentation.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.tabs.SettingsTab
import com.henryhiles.qweather.presentation.tabs.TodayTab
import com.henryhiles.qweather.presentation.tabs.WeekTab

@Composable
fun BottomBar(navigator: TabNavigator) {
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