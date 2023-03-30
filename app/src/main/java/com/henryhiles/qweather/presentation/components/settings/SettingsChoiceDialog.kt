package com.henryhiles.qweather.presentation.components.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.EnumRadioController

@Composable
inline fun <reified E : Enum<E>> SettingsChoiceDialog(
    visible: Boolean = false,
    default: E,
    noinline title: @Composable () -> Unit,
    crossinline labelFactory: (E) -> String = { it.toString() },
    noinline onRequestClose: () -> Unit = {},
    crossinline description: @Composable () -> Unit = {},
    noinline onChoice: (E) -> Unit = {},
) {

    var choice by remember { mutableStateOf(default) }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        AlertDialog(
            onDismissRequest = { onRequestClose() },
            title = title,
            text = {
                description()
                EnumRadioController(
                    default,
                    labelFactory
                ) { choice = it }
            },
            confirmButton = {
                FilledTonalButton(onClick = { onChoice(choice) }) {
                    Text(text = stringResource(id = R.string.action_confirm))
                }
            }
        )
    }

}