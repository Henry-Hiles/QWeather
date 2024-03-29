package com.henryhiles.qweather

import android.app.Application
import com.henryhiles.qweather.di.appModule
import com.henryhiles.qweather.di.managerModule
import com.henryhiles.qweather.di.repositoryModule
import com.henryhiles.qweather.di.screenModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QWeather : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QWeather)
            modules(
                appModule, repositoryModule, screenModelModule, managerModule
            )
        }
    }
}
