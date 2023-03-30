package com.henryhiles.qweather.presentation.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.henryhiles.qweather.R

object WeekTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tab_weekly)
            val icon = rememberVectorPainter(Icons.Default.DateRange)

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
        Text(text = "Week Screen")
    }
}