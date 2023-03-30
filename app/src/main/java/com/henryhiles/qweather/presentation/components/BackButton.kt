package com.henryhiles.qweather.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.LocalNavigator
import com.henryhiles.qweather.R

@Composable
fun BackButton() {
    val nav = LocalNavigator.current

    if (nav?.canPop == true) {
        IconButton(onClick = { nav.pop() }) {
            Icon(Icons.Filled.ArrowBack, stringResource(R.string.action_back))
        }
    }
}