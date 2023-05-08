package com.henryhiles.qweather.presentation.components.location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.henryhiles.qweather.R
import com.henryhiles.qweather.presentation.screen.LocationPickerScreen
import com.henryhiles.qweather.presentation.screenmodel.LocationPreferenceManager
import org.koin.androidx.compose.get

@Composable
fun LocationsDrawer(
    drawerState: DrawerState,
    onClose: () -> Unit,
    children: @Composable () -> Unit
) {
    val locationPreferenceManager: LocationPreferenceManager = get()
    val navigator = LocalNavigator.current?.parent

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    val locations = locationPreferenceManager.locations

                    Text(
                        text = stringResource(id = R.string.locations),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    locations.forEachIndexed { index, data ->
                        val selected = index == locationPreferenceManager.selectedIndex
                        NavigationDrawerItem(
                            label = { Text(text = data.location) },
                            selected = selected,
                            onClick = {
                                onClose()
                                locationPreferenceManager.selectedIndex = index
                            },
                            badge = {
                                IconButton(onClick = {
                                    locationPreferenceManager.locations -= data
                                    if (selected) locationPreferenceManager.selectedIndex = 0
                                    if (locationPreferenceManager.locations.isEmpty()) navigator?.push(
                                        LocationPickerScreen()
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(
                                            id = R.string.action_delete
                                        )
                                    )
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = R.string.location_add)) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.location_add)
                            )
                        },
                        selected = true,
                        onClick = { navigator?.push(LocationPickerScreen()) },
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        children()
    }
}