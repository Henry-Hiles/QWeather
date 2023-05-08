package com.henryhiles.qweather.presentation.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SmallToolbar(
    backButton: Boolean = false,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = { if (backButton) BackButton() },
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
    )
}