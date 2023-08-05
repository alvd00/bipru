package com.example.bipru.domen.repository

interface MainRepository {

    var vrp : String

    var vrc : String

    var driversLicense : String

    var isFinished : Boolean

    fun saveUserData(vrp: String, vrc: String, driversLicense: String, isFinished: Boolean)

}