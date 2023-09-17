package com.golnaz.home.di

import com.golnaz.home.data.ApiHelper
import com.golnaz.home.data.ApiHelperImpl
import com.golnaz.home.network.provideApiService
import com.golnaz.home.network.provideOkHttpClient
import com.golnaz.home.network.provideRetrofit
import org.koin.dsl.module

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}