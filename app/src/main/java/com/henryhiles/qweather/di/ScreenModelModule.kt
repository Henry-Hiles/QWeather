package com.henryhiles.qweather.di

import com.henryhiles.qweather.presentation.screenmodel.AppearancePreferencesScreenModel
import com.henryhiles.qweather.presentation.screenmodel.UnitPreferencesScreenModel
import com.henryhiles.qweather.presentation.screenmodel.DailyWeatherScreenModel
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherScreenModel
import com.henryhiles.qweather.presentation.screenmodel.LocationPickerScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::AppearancePreferencesScreenModel)
    factoryOf(::UnitPreferencesScreenModel)
    factoryOf(::LocationPickerScreenModel)
    factoryOf(::HourlyWeatherScreenModel)
    factoryOf(::DailyWeatherScreenModel)
}