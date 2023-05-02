package com.henryhiles.qweather.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}