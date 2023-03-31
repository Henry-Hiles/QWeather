package com.henryhiles.qweather.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmallToolbar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = { BackButton() },
        actions = actions,
    )
}