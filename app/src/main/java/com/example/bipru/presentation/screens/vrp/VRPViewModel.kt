package com.example.bipru.presentation.screens.vrp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bipru.domen.model.VRPType
import com.example.bipru.domen.repository.MainRepository
import com.example.bipru.presentation.base.BaseViewModel
import com.example.bipru.utils.extentions.convertToCyrillic
import com.example.bipru.utils.extentions.isContainsCyrillic

class VRPViewModel(
    private val repository: MainRepository,
) : BaseViewModel() {
    var vrpCode: String = ""
        set(value) {
            field = value
            validate()
        }
    val isFinishedResults = repository.isFinished
    val vrp = repository.vrp
    val vrc = repository.vrc

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    init {
        vrpCode = repository.vrp
    }

    private fun validate() {
        var isValid = false
        val russianVrp = vrpCode.convertToCyrillic()
        if (russianVrp.length == VRP_LENGTH) {
            isValid = if (russianVrp[0].isDigit()) {
                typedValidate(russianVrp, VRPType.FIFTH)
            } else if (russianVrp[1].isDigit()) typedValidate(russianVrp, VRPType.FIRST)
            else typedValidate(russianVrp, VRPType.SECOND)
        }
        _isButtonEnabled.postValue(isValid)
    }

    private fun typedValidate(vrp: String, type: VRPType): Boolean {
        var isValid = false
        vrp.forEachIndexed { index, symbol ->
            isValid = when (type) {
                VRPType.FIRST -> {
                    if (index == 0 || index == 4 || index == 5)
                        symbol.isContainsCyrillic()
                    else symbol.isDigit()
                }

                VRPType.SECOND -> {
                    if (index == 0 || index == 1)
                        symbol.isContainsCyrillic()
                    else symbol.isDigit()
                }

                VRPType.FIFTH -> {
                    if (index == 4 || index == 5)
                        symbol.isContainsCyrillic()
                    else symbol.isDigit()
                }

                else -> false
            }
            if (!isValid) return false
        }
        return isValid
    }

    fun skipVRP() {
        repository.vrp = ""
        repository.vrc = ""
    }

    companion object {
        const val VRP_LENGTH = 8
    }
}