package com.henryhiles.qweather.presentation.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmallToolbar(
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    backButton: Boolean = true
) {
    TopAppBar(
        title = title,
        navigationIcon = { if (backButton) BackButton() },
        actions = actions,
    )
}