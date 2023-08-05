package com.example.bipru.locator

import com.example.bipru.presentation.screens.drivers_license.DriversLicenseViewModel
import com.example.bipru.presentation.screens.results.ResultsViewModel
import com.example.bipru.presentation.screens.vrc.VRCViewModel
import com.example.bipru.presentation.screens.vrp.VRPViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { VRPViewModel(get()) }
    viewModel { VRCViewModel(get()) }
    viewModel { DriversLicenseViewModel(get()) }
    viewModel { ResultsViewModel(get()) }
}