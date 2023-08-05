package com.example.bipru.presentation.screens.drivers_license

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bipru.domen.repository.MainRepository
import com.example.bipru.presentation.base.BaseViewModel
import com.example.bipru.utils.extentions.convertToCyrillic
import com.example.bipru.utils.extentions.isContainsCyrillic

class DriversLicenseViewModel(
    private val repository: MainRepository
) : BaseViewModel() {
    val vrp = repository.vrp
    val vrc = repository.vrc
    var driversLicenseCode: String = ""
        set(value) {
            field = value
            validate()
        }

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled
    init {
        driversLicenseCode = repository.driversLicense
    }
    private fun validate() {
        var isValid = false
        val russianVrc = driversLicenseCode.convertToCyrillic()
        if (russianVrc.length == VRC_LENGTH) {
            val secondChar = russianVrc[2]
            val thirdChar = russianVrc[3]
            //если словарь общих букв(латинских и русских) содержат 3 и 4 символ или какой-либо из этих символов "Ч", то 3 и 4 символ валидны
            isValid = if (!secondChar.isDigit() && !thirdChar.isDigit())
                ((secondChar.isContainsCyrillic() || secondChar == 'З') && (thirdChar.isContainsCyrillic() || thirdChar == 'Ч'))
            else validateDriversLicense(russianVrc)
        }
        _isButtonEnabled.postValue(isValid)
    }

    private fun validateDriversLicense(vrc: String): Boolean {
        var isValid = false
        vrc.forEach { symbol ->
            isValid = symbol.isDigit()
            if (!isValid) return false
        }
        return isValid
    }

    fun saveDriversLicense(license: String) {
        repository.saveUserData(vrp, vrc, license, FINISH_ENTERING_RESULTS)

    }

    companion object {
        const val VRC_LENGTH = 10
        const val FINISH_ENTERING_RESULTS = true
    }
}