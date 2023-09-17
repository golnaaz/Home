package com.golnaz.home

import android.app.Application
import com.golnaz.home.di.appModule
import com.golnaz.home.di.repoModule
import com.golnaz.home.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}