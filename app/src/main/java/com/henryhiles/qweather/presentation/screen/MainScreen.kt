package com.henryhiles.qweather.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.henryhiles.qweather.R
import com.henryhiles.qweather.domain.util.NavigationTab
import com.henryhiles.qweather.presentation.components.location.LocationsDrawer
import com.henryhiles.qweather.presentation.components.navigation.BottomBar
import com.henryhiles.qweather.presentation.components.navigation.SmallToolbar
import com.henryhiles.qweather.presentation.screenmodel.LocationPreferenceManager
import com.henryhiles.qweather.presentation.tabs.TodayTab
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

class MainScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val locationPreferenceManager: LocationPreferenceManager = get()
        val drawerState =
            rememberDrawerState(initialValue = DrawerValue.Closed)
        val coroutineScope = rememberCoroutineScope()
        TabNavigator(tab = TodayTab) {
            LocationsDrawer(
                drawerState = drawerState,
                onClose = { coroutineScope.launch { drawerState.close() } }) {
                Scaffold(
                    topBar = {
                        SmallToolbar(
                            title = {
                                with(locationPreferenceManager) {
                                    Text(
                                        text = locations[selectedIndex].location,
                                        maxLines = 1,
                                        modifier = Modifier.basicMarquee()
                                    )
                                }
                            },
                            actions = {
                                (it.current as? NavigationTab)?.Actions()
                            }
                        ) {
                            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = stringResource(id = R.string.location_picker_open)
                                )
                            }
                        }
                    },
                    bottomBar = {
                        BottomBar(navigator = it)
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        CurrentScreen()
                    }
                }
            }
        }
    }
}