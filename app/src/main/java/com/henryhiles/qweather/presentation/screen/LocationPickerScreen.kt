package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.components.navigation.SmallToolbar
import com.henryhiles.qweather.presentation.screenmodel.LocationPickerScreenModel


class LocationPickerScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: LocationPickerScreenModel = getScreenModel()
        var latitude by remember { mutableStateOf(screenModel.prefs.latitude) }
        var longitude by remember { mutableStateOf(screenModel.prefs.longitude) }
        var location by remember { mutableStateOf(screenModel.prefs.location) }
        var locationSearch by remember { mutableStateOf("") }
        var isAboutOpen by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.current
        val context = LocalContext.current

        Scaffold(floatingActionButton = {
            FloatingActionButton(onClick = {
                screenModel.prefs.location = location
                screenModel.prefs.latitude = latitude
                screenModel.prefs.longitude = longitude
                navigator?.push(MainScreen())
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.action_apply)
                )
            }
        }) {
            screenModel.state.error?.let {
                AlertDialog(
                    onDismissRequest = {},
                    confirmButton = {},
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
                Column {
                    SmallToolbar(
                        title = { Text(text = stringResource(id = R.string.location_choose)) },
                        actions = {
                            IconButton(
                                onClick = { isAboutOpen = true }) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = stringResource(id = R.string.help_screen)
                                )
                            }
                        })
                    Column(modifier = Modifier.padding(16.dp)) {
                        if (isAboutOpen) AlertDialog(
                            title = { Text(text = stringResource(id = R.string.location_choose)) },
                            text = { Text(text = stringResource(id = R.string.help_location_picker)) },
                            onDismissRequest = { isAboutOpen = false },
                            confirmButton = {
                                TextButton(
                                    onClick = { isAboutOpen = false }) {
                                    Text(text = stringResource(id = R.string.action_confirm))
                                }
                            })

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
                                if (locationSearch == "")
                                    IconButton(onClick = {

                                    }) {
                                        Icon(
                                            imageVector = Icons.Outlined.MyLocation,
                                            contentDescription = stringResource(id = R.string.location_auto_pick)
                                        )
                                    }
                                else
                                    IconButton(onClick = {
                                        screenModel.loadGeolocationInfo(
                                            locationSearch
                                        )
                                    }) {
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
                        ) else screenModel.state.locations?.let {
                            LazyColumn {
                                items(it) {
                                    val locationText by remember {
                                        mutableStateOf(
                                            context.getString(
                                                R.string.location_string,
                                                it.city, it.admin, it.country
                                            )
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Card(modifier = Modifier.clickable {
                                        location = locationText
                                        longitude = it.longitude
                                        latitude = it.latitude
                                    }) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            if (location == locationText) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = stringResource(
                                                        id = R.string.selected
                                                    ),
                                                    modifier = Modifier.height(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                            }
                                            Text(text = locationText)
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
