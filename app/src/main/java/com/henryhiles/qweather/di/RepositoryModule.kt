package com.henryhiles.qweather.di

import com.henryhiles.qweather.data.repository.WeatherRepositoryImpl
import com.henryhiles.qweather.domain.repository.WeatherRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//    @Binds
//    @Singleton
//    abstract fun bindWeatherRepository(weatherRepository: WeatherRepository): WeatherRepository
//}

val repositoryModule = module {
    singleOf(::WeatherRepositoryImpl) bind WeatherRepository::class
}