package com.henryhiles.qweather.di

import com.henryhiles.qweather.presentation.screenmodel.AppearancePreferenceManager
import com.henryhiles.qweather.presentation.screenmodel.LocationPreferenceManager

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::AppearancePreferenceManager)
    singleOf(::LocationPreferenceManager)
}