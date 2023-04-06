package com.henryhiles.qweather.presentation.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.henryhiles.qweather.R
import com.henryhiles.qweather.domain.util.NavigationTab
import com.henryhiles.qweather.presentation.components.settings.SettingsCategory
import com.henryhiles.qweather.presentation.screen.AboutScreen
import com.henryhiles.qweather.presentation.screen.AppearanceSettingsScreen

object SettingsTab : NavigationTab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tab_settings)
            val icon = rememberVectorPainter(Icons.Default.Settings)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column {
            SettingsCategory(
                icon = Icons.Outlined.Palette,
                text = stringResource(R.string.settings_appearance),
                subtext = stringResource(R.string.settings_appearance_description),
                destination = ::AppearanceSettingsScreen
            )
        }
    }

    @Composable
    override fun Actions() {
        val navigator = LocalNavigator.currentOrThrow.parent

        IconButton(onClick = { navigator?.push(AboutScreen()) }) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.action_open_about)
            )
        }
    }
}