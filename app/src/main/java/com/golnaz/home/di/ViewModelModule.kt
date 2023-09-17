package com.golnaz.home.di

import com.golnaz.home.ui.viewmodel.DetailViewModel
import com.golnaz.home.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::DetailViewModel)
}