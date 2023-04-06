package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.henryhiles.qweather.presentation.components.navigation.LargeToolbar

class AboutScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(topBar = { LargeToolbar(title = { Text(text = "About") }, backButton = true) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
            }
        }
    }
}