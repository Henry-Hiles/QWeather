package com.henryhiles.qweather.di

import com.henryhiles.qweather.presentation.screenmodel.AppearancePreferencesScreenModel
import com.henryhiles.qweather.presentation.screenmodel.DailyWeatherScreenModel
import com.henryhiles.qweather.presentation.screenmodel.HourlyWeatherScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::AppearancePreferencesScreenModel)
    factoryOf(::HourlyWeatherScreenModel)
    factoryOf(::DailyWeatherScreenModel)
}