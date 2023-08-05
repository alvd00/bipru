package com.example.bipru.presentation.screens.results

import com.example.bipru.domen.repository.MainRepository
import com.example.bipru.presentation.base.BaseViewModel

class ResultsViewModel(private val repository: MainRepository) : BaseViewModel() {
    val vrp = repository.vrp
    val vrc = repository.vrc
    val driversLicense = repository.driversLicense

    fun backFromResults(){
        repository.isFinished = false
    }
}