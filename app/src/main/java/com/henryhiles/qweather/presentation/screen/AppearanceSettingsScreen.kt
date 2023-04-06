package com.henryhiles.qweather.presentation.screen

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.LargeToolbar
import com.henryhiles.qweather.presentation.components.settings.SettingsItemChoice
import com.henryhiles.qweather.presentation.components.settings.SettingsSwitch
import com.henryhiles.qweather.presentation.screenmodel.AppearancePreferencesScreenModel

class AppearanceSettingsScreen : Screen {

    @Composable
    override fun Content() = Screen()

    @Composable
    private fun Screen(
        screenModel: AppearancePreferencesScreenModel = getScreenModel()
    ) {
        val ctx = LocalContext.current

        Scaffold(topBar = { Toolbar() }) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {

                SettingsSwitch(
                    label = stringResource(R.string.appearance_monet),
                    secondaryLabel = stringResource(R.string.appearance_monet_description),
                    pref = screenModel.prefs.monet,
                    disabled = Build.VERSION.SDK_INT < Build.VERSION_CODES.S
                ) { screenModel.prefs.monet = it }

                SettingsItemChoice(
                    label = stringResource(R.string.appearance_theme),
                    pref = screenModel.prefs.theme,
                    labelFactory = { ctx.getString(it.label) }
                ) { screenModel.prefs.theme = it }

            }
        }
    }

    @Composable
    private fun Toolbar(
    ) {
        LargeToolbar(
            title = stringResource(R.string.settings_appearance),
        )
    }

}