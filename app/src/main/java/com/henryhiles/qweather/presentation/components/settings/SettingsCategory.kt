package com.henryhiles.qweather.presentation.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

@Composable
fun SettingsCategory(
    icon: ImageVector,
    text: String,
    subtext: String,
    destination: (() -> Screen)? = null
) {
    val screen = destination?.invoke()
    val navigator = LocalNavigator.current?.parent

    Box(
        modifier = Modifier
            .clickable {
                screen?.let { navigator?.push(it) }
            }
    ) {
        SettingItem(
            icon = { Icon(icon, null) },
            text = { Text(text) },
            secondaryText = { Text(subtext) }
        )
    }
}