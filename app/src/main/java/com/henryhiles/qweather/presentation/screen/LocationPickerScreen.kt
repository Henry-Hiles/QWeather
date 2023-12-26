package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.navigation.SmallToolbar
import com.henryhiles.qweather.presentation.screenmodel.LocationPickerScreenModel

class LocationPickerScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: LocationPickerScreenModel = getScreenModel()
        var locationSearch by remember { mutableStateOf("") }
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.imePadding(),
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SmallToolbar(
                    title = { Text(text = stringResource(id = R.string.location_choose)) },
                    backButton = screenModel.prefs.locations.isNotEmpty(),
                )
                screenModel.state.error?.let {
                    AlertDialog(
                        onDismissRequest = { navigator.pop() },
                        confirmButton = {
                            TextButton(onClick = { navigator.pop() }) {
                                Text(text = stringResource(id = R.string.action_confirm))
                            }
                        },
                        title = { Text(text = stringResource(id = R.string.error)) },
                        text = {
                            SelectionContainer {
                                Text(
                                    text = it,
                                )
                            }
                        },
                    )
                } ?: kotlin.run {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            label = { Text(text = stringResource(id = R.string.location)) },
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(onSearch = {
                                screenModel.loadGeolocationInfo(
                                    locationSearch
                                )
                            }),
                            maxLines = 1,
                            value = locationSearch,
                            onValueChange = { locationSearch = it },
                            trailingIcon = {
                                IconButton(onClick = {
                                    screenModel.loadGeolocationInfo(
                                        locationSearch
                                    )
                                }, enabled = locationSearch != "") {
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = stringResource(id = R.string.action_search)
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (screenModel.state.isLoading) CircularProgressIndicator(
                            modifier = Modifier
                                .align(
                                    Alignment.CenterHorizontally
                                )
                                .padding(16.dp)
                        ) else screenModel.state.locations?.let { results ->
                            if (results.isEmpty()) Card {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "No locations found")
                                }
                            }
                            LazyColumn {
                                items(results) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Card(modifier = Modifier.clickable {
                                        locationSearch = ""
                                        with(screenModel.prefs) {
                                            if (it !in locations) {
                                                locations += it
                                                selectedIndex =
                                                    locations.count() - 1
                                            }
                                        }

                                        with(navigator) {
                                            if (canPop) pop() else push(MainScreen())
                                        }
                                    }) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = it.location)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
