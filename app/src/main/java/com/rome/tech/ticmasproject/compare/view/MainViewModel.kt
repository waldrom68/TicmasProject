package com.rome.tech.ticmasproject.compare.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.rome.tech.ticmasproject.compare.model.Compare


class MainViewModel : ViewModel() {
    // TODO: reemplazar texto estatico por texto de resources
    val result: String = "Debe ingresar ambos valores para comparar"
    private var _result = result
    val compare: LiveData<Compare> get() = _compare
    private var _compare = MutableLiveData<Compare>(Compare(_result))

    private fun compare(str1: String, str2: String): String {
        // TODO: reemplazar texto estatico por texto de resources
        return when (str1 == str2) {
            true -> "Textos ingresados iguales"
            else -> "Textos ingresados diferentes"
        }
    }

    private fun updateCompare(text: String) {
        _compare.value = Compare(text)

    }

    fun actionCompare(str1: String, str2: String) {
        if (str1.isNotEmpty() && str2.isNotEmpty())
            _result = this.compare(str1, str2)
        else {
            _result = result
        }

        updateCompare(_result)
    }

    fun actionReset() {
        // TODO: llamar a este metodo para resetear valores
        updateCompare(result)
    }

}