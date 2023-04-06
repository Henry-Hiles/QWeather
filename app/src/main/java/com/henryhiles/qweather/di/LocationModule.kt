package com.henryhiles.qweather.di

import com.henryhiles.qweather.domain.location.LocationTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val locationModule = module {
    singleOf(::LocationTracker)
}