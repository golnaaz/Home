package com.golnaz.home.di

import com.golnaz.home.domain.provideMainRepository
import org.koin.dsl.module

val repoModule = module {
    single { provideMainRepository(get()) }
}