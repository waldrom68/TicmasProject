package com.rome.tech.ticmasproject.compare.model

import com.rome.tech.ticmasproject.compare.`interface`.ElementToCompareInterface

data class EvaluateStrings(val str1:String, val str2:String): ElementToCompareInterface {
     override fun evaluate(): Boolean {
         return str1 == str2
     }
 }
