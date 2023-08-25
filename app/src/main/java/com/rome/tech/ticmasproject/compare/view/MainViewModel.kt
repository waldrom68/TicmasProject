package com.rome.tech.ticmasproject.compare.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.rome.tech.ticmasproject.compare.model.ComparisonResult
import com.rome.tech.ticmasproject.compare.model.EvaluateStrings


class MainViewModel : ViewModel() {
    // TODO: reemplazar texto estatico por texto de resources
    val result: String = "Debe ingresar ambos valores para comparar"
    private var _result = result

    val compare: LiveData<ComparisonResult> get() = _compare
    private var _compare = MutableLiveData<ComparisonResult>(ComparisonResult(_result))


    fun actionCompare(str1: String, str2: String) {
        // The button has been pressed to compare the EditText
        _result = if (str1.isNotEmpty() && str2.isNotEmpty()) when (EvaluateStrings(
            str1, str2
        ).evaluate()) {
            // TODO: reemplazar texto estatico por texto de resources
            true -> "Textos ingresados iguales"
            else -> "Textos ingresados diferentes"
        }
        else {
            result
        }

        updateCompare(_result)
    }

    fun actionReset() {
        // TODO: llamar a este metodo para resetear valores
        updateCompare(result)
    }

    private fun updateCompare(text: String) {
        // The observed attribute is updated
        _compare.value = ComparisonResult(text)

    }

    // Version anterior al refactoreo del codigo
//    private fun compare(str1: String, str2: String): String {
//        return when (EvaluateStrings(str1, str2).evaluate()) {
//            true -> "Textos ingresados iguales"
//            else -> "Textos ingresados diferentes"
//        }
//    }


}