package com.rome.tech.ticmasproject.compare.model

import com.rome.tech.ticmasproject.compare.`interface`.ElementToCompareInterface

class Evaluate {
    fun evaluate(elemento: ElementToCompareInterface):Boolean {
        return elemento.evaluate()
    }
}


