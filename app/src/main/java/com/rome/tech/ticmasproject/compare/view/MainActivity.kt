package com.rome.tech.ticmasproject.compare.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rome.tech.ticmasproject.R
import com.rome.tech.ticmasproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Subscribo el seguimiento del atributo "compare" en esta vista
        mainViewModel.comparisonResult.observe(this) {
            /* TODO por MVVM no se recomienda utilizar el @string en el ViewModel().
                Por ahora su método devuelve valores tipo indexOf: true -> 1, false -> 0, null -> -1
                Investigar si existe otra forma limpia.
            */
            binding.result.text = when (it.result) {
                0 -> { getString(R.string.notOkResult) }
                1 -> { getString(R.string.okResult) }
                else -> { getString(R.string.resetResult) }
            }
            this.hideVirtualKkeyboard();
        }

        binding.actionCompare.setOnClickListener {
            this.hideVirtualKkeyboard();
            // Obtengo de la UI los valores de los EditText para comparar
            mainViewModel.actionCompare(
                binding.str1.text.toString(), binding.str2.text.toString()
            )
        }

    }


    private fun hideVirtualKkeyboard() {
        // TODO relocate this code. Maybe in a service
        // on below line getting current view.
        val view: View? = this.currentFocus
        // on below line checking if view is not null.
        if (view != null) {
            // on below line we are creating a variable
            // for input manager and initializing it.
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            // on below line hiding our keyboard.
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}