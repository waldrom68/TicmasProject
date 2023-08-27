package com.rome.tech.ticmasproject.compare.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rome.tech.ticmasproject.compare.model.ComparisonResult
import com.rome.tech.ticmasproject.compare.model.EvaluateStrings


// Business logic
class MainViewModel : ViewModel() {

    // TODO: reemplazar texto estatico por texto de resources
    private val result: String = "Debe ingresar ambos valores para comparar"
    // private val result: String = ""
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

        _result = if (str1.isNotEmpty() && str2.isNotEmpty()) when (EvaluateStrings(
            str1.toString(), str2.toString()
        ).evaluate()) {
            // TODO: reemplazar texto estatico por texto de resources
            true -> "Textos ingresados iguales"
            else -> "Textos ingresados diferentes"
        }
        else {
            result
        }
        updateResult(_result)
    }

    fun actionReset() {
        // TODO: llamar a este metodo para resetear valores
        updateResult(result)
    }


}