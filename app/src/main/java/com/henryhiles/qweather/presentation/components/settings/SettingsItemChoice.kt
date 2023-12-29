package com.henryhiles.qweather.presentation.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
inline fun <reified E : Enum<E>> SettingsItemChoice(
    label: String,
    secondaryLabel: String? = null,
    title: String = label,
    disabled: Boolean = false,
    pref: E,
    crossinline labelFactory: (E) -> String = { it.toString() },
    crossinline onPrefChange: (E) -> Unit,
) {
    val choiceLabel = labelFactory(pref)
    var opened by remember {
        mutableStateOf(false)
    }

    SettingItem(
        modifier = Modifier.clickable { opened = true },
        text = { Text(text = label) },
        secondaryText = { if (secondaryLabel != null) Text(text = secondaryLabel) }
    ) {
        SettingsChoiceDialog(
            visible = opened,
            title = { Text(title) },
            default = pref,
            labelFactory = labelFactory,
            onRequestClose = {
                opened = false
            },
            onChoice = {
                opened = false
                onPrefChange(it)
            }
        )
        FilledTonalButton(onClick = { opened = true }, enabled = !disabled) {
            Text(choiceLabel)
        }
    }
}