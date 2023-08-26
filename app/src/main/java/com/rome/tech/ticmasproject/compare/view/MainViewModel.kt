package com.rome.tech.ticmasproject.compare.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.rome.tech.ticmasproject.compare.model.ComparisonResult
import com.rome.tech.ticmasproject.compare.model.EvaluateStrings


class MainViewModel : ViewModel() {
    // TODO: reemplazar texto estatico por texto de resources
    private val result: String = "Debe ingresar ambos valores para comparar"
    private var _result: String = result

    val comparisonResult: LiveData<ComparisonResult> get() = _comparisonResult
    private var _comparisonResult: MutableLiveData<ComparisonResult> =
        MutableLiveData(ComparisonResult(_result))

    private fun updateResult(text: String) {
        // The observed attribute is updated
        _comparisonResult.value = ComparisonResult(text)
    }

    // User interaction.
    // The button has been pressed to compare the EditText
    fun actionCompare(str1: String, str2: String) {
        // TODO: str1 y str2 deben leerse aqui y no recibirse como parametro
        _result = if (str1.isNotEmpty() && str2.isNotEmpty()) when (EvaluateStrings(
            str1, str2
        )
            .evaluate()) {
            // TODO: reemplazar texto estatico por texto de resources
            true -> "Textos ingresados iguales"
            else -> "Textos ingresados diferentes"
        }
        else {
            result
        }
        updateResult(_result)
    }

//    fun actionReset() {
//        // TODO: llamar a este metodo para resetear valores
//        updateCompare(result)
//    }



}