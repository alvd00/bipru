package com.example.bipru.data.repository_impl

import com.example.bipru.data.prefs.BipPref
import com.example.bipru.domen.repository.MainRepository

class MainRepositoryImpl(private val prefs: BipPref) : MainRepository {

    override var vrp: String
        get() = prefs.vrp
        set(value) {
            prefs.vrp = value
        }

    override var vrc: String
        get() = prefs.vrc
        set(value) {
            prefs.vrc = value
        }

    override var driversLicense: String
        get() = prefs.driversLicense
        set(value) {
            prefs.driversLicense = value
        }

    override var isFinished: Boolean
        get() = prefs.isFinished
        set(value) {
            prefs.isFinished = value
        }

    override fun saveUserData(
        vrp: String,
        vrc: String,
        driversLicense: String,
        isFinished: Boolean
    ) {
        prefs.vrp = vrp
        prefs.vrc = vrc
        prefs.driversLicense = driversLicense
        prefs.isFinished = isFinished
    }

}