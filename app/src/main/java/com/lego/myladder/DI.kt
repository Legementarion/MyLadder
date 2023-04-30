package com.lego.myladder

import com.lego.myladder.presentation.ui.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single { MainViewModel() }
}