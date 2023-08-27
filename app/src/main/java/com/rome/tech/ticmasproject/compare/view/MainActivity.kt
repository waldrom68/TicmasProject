package com.rome.tech.ticmasproject.compare.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
            println("Actualizando UI por cambio en .compare.model")
            binding.result.text = it.result
        }

        binding.actionCompare.setOnClickListener {
            // Obtengo de la UI los valores de los EditText para comparar
            mainViewModel.actionCompare(
                binding.str1.text.toString(),
                binding.str2.text.toString()
            )
        }


        // TODO: asignar los valores de los input en blanco
        binding.actionReset.setOnClickListener {
            binding.str1.text = null
            binding.str2.text = null
        }

    }

}