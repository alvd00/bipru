package com.example.bipru.data.prefs

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.bipru.utils.extentions.orFalse

class BipPref(context: Context) {

    private val sp = PreferenceManager.getDefaultSharedPreferences(context)

    var vrp: String
        get() = getString(DataStoreKeys.VRP)
        set(value) {
            setString(DataStoreKeys.VRP, value)
        }

    var vrc: String
        get() = getString(DataStoreKeys.VRC)
        set(value) {
            setString(DataStoreKeys.VRC, value)
        }

    var driversLicense: String
        get() = getString(DataStoreKeys.DRIVERS_LICENSE)
        set(value) {
            setString(DataStoreKeys.DRIVERS_LICENSE, value)
        }

    var isFinished: Boolean
        get() = getBoolean(DataStoreKeys.FINISH)
        set(value) {
            setBoolean(DataStoreKeys.FINISH, value)
        }

    //region ================= Private =================

    private fun setString(keyName: String, keyValue: String) {
        sp.edit().putString(keyName, keyValue).apply()
    }

    private fun setBoolean(keyName: String, keyValue: Boolean) {
        sp.edit().putBoolean(keyName, keyValue).apply()
    }

    private fun getString(keyName: String): String =
        sp.getString(keyName, "").orEmpty()

    private fun getBoolean(keyName: String): Boolean =
        sp.getBoolean(keyName, false).orFalse()

}