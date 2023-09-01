package com.rome.tech.ticmasproject.compare.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rome.tech.ticmasproject.compare.model.ComparisonResult
import com.rome.tech.ticmasproject.compare.model.EvaluateStrings


// Business logic
class MainViewModel : ViewModel() {

    private val result: Int = -1
    private var _result: Int = result

    val comparisonResult: LiveData<ComparisonResult> get() = _comparisonResult
    private var _comparisonResult: MutableLiveData<ComparisonResult> =
        MutableLiveData(ComparisonResult(_result))


    private fun updateResult(newValue: Int) {
        // The observed attribute is updated
        _comparisonResult.value = ComparisonResult(newValue)
    }

    // User interaction.
    // The button has been pressed to compare the EditText
    fun actionCompare(str1: String, str2: String) {
        /* TODO por MVVM no se recumienda utilizar aqui el @string.
            Por ahora el método devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
            Investigar si existe otra forma limpia.
        */
        _result = if (str1.isNotEmpty() && str2.isNotEmpty()) when (EvaluateStrings(
            str1.toString(), str2.toString()
        ).evaluate()) {
            true -> 1
            else -> 0
        }
        else {
            result
        }
        updateResult(_result)
    }

//    fun actionReset() {
//        updateResult(result)
//    }


}
