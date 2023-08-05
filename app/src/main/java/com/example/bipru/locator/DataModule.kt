package com.example.bipru.locator

import com.example.bipru.data.repository_impl.MainRepositoryImpl
import com.example.bipru.data.prefs.BipPref
import com.example.bipru.domen.repository.MainRepository
import org.koin.dsl.module

val dataModule = module {
    single { BipPref(get()) }
    single<MainRepository> { MainRepositoryImpl(prefs = get()) }
}