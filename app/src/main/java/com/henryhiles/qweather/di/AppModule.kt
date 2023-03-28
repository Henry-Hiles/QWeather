package com.henryhiles.qweather.di

import com.henryhiles.qweather.data.remote.WeatherApi
import com.henryhiles.qweather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//    @Provides
//    @Singleton
//    fun provideWeatherApi(): WeatherApi {
//        return Retrofit.Builder().baseUrl("https://api.open-meteo.com")
//            .addConverterFactory(MoshiConverterFactory.create()).build().create()
//    }
//
//    @Provides
//    @Singleton
//    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
//        return LocationServices.getFusedLocationProviderClient(app)
//    }
//}

val appModule = module {
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl("https://api.open-meteo.com")
            .addConverterFactory(MoshiConverterFactory.create()).build().create()
    }

    singleOf(::provideWeatherApi)
//    single {
//        LocationServices.getFusedLocationProviderClient(get<Application>())
//    }
    viewModelOf(::WeatherViewModel)
}