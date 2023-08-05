package com.example.bipru

import android.app.Application
import com.example.bipru.locator.dataModule
import com.example.bipru.locator.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    dataModule
                )
            )
        }
    }
}