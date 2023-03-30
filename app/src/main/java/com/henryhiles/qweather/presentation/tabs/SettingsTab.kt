package com.henryhiles.qweather.presentation.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.LargeToolbar
import com.henryhiles.qweather.presentation.components.settings.SettingsCategory
import com.henryhiles.qweather.presentation.screen.AppearanceSettingsScreen

object SettingsTab : Tab {
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
        Scaffold(
            topBar = { Toolbar() },
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {

                SettingsCategory(
                    icon = Icons.Outlined.Palette,
                    text = stringResource(R.string.settings_appearance),
                    subtext = stringResource(R.string.settings_appearance_description),
                    destination = ::AppearanceSettingsScreen
                )
            }
        }
    }

    @Composable
    private fun Toolbar() {
        LargeToolbar(
            title = stringResource(R.string.tab_settings),
        )
    }
}