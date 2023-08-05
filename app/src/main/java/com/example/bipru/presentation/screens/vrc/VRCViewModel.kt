package com.example.bipru.presentation.screens.vrc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bipru.domen.repository.MainRepository
import com.example.bipru.presentation.base.BaseViewModel
import com.example.bipru.utils.extentions.convertToCyrillic
import com.example.bipru.utils.extentions.isContainsCyrillic

class VRCViewModel(
    private val repository: MainRepository
) : BaseViewModel() {
    var vrcCode: String = ""
        set(value) {
            field = value
            validate()
        }

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private fun validate() {
        var isValid = false
        val russianVrc = vrcCode.convertToCyrillic()
        if (russianVrc.length == VRC_LENGTH) {
            val secondChar = russianVrc[2]
            val thirdChar = russianVrc[3]
            //если словарь общих букв(латинских и русских) содержат 3 и 4 символ или какой-либо из этих символов "З", то 3 и 4 символ валидны
            isValid = if (!secondChar.isDigit() && !thirdChar.isDigit())
                ((secondChar.isContainsCyrillic() || secondChar == 'З') && (thirdChar.isContainsCyrillic() || thirdChar == 'З'))
            else validateVRC(russianVrc)
        }
        _isButtonEnabled.postValue(isValid)
    }

    private fun validateVRC(vrc: String): Boolean {
        var isValid = false
        vrc.forEach { symbol ->
            isValid = symbol.isDigit()
            if (!isValid) return false
        }
        return isValid
    }

    fun saveData(vrp: String) {
        repository.vrc = vrcCode
        repository.vrp = vrp
    }

    companion object {
        const val VRC_LENGTH = 10
    }
}