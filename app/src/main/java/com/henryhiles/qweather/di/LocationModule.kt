package com.henryhiles.qweather.di

import com.henryhiles.qweather.domain.location.LocationTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class LocationModule {
//    @Binds
//    @Singleton
//    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker
//}


val locationModule = module {
    singleOf(::LocationTracker)
}