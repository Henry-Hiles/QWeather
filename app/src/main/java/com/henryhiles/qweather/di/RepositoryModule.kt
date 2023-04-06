package com.henryhiles.qweather.di

import com.henryhiles.qweather.domain.repository.WeatherRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::WeatherRepository)
}