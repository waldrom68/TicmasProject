package com.rome.tech.ticmasproject.compare.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
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
        mainViewModel.compare.observe(this) {
            println("Actualizando UI por cambio en .compare.model")
            binding.result.text = it.result
        }

        binding.actionCompare.setOnClickListener {
            // Obtengo de la UI los valores de los EditText para utilizarlo en el metodo
            // TODO: Texto para el readme - documentation
            // Se compara cadena de caracteres,y no los textos por ello no se utiliza .trim()
            // antes de la comparación.
            // P.ej:
            //      "Hola" == " Hola  " -> False
            val str1: String = binding.str1.text.toString()
            val str2: String = binding.str2.text.toString()

            mainViewModel.actionCompare(str1, str2)
        }

        binding.actionReset.setOnClickListener {
            // TODO: asignar los valores de los input en blanco
            mainViewModel.actionReset()
        }


    }

}