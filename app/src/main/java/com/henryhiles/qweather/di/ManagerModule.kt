package com.henryhiles.qweather.di

import com.henryhiles.qweather.presentation.screenmodel.AppearancePreferenceManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::AppearancePreferenceManager)
}