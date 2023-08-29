package com.rome.tech.ticmasproject.compare.mock

class MockTestData {

    fun diferentData(): List<List<String>> {
        return listOf(
            listOf("Hola", "hola"),
            listOf("hola", "hOla"),
            listOf("hola", " hola"),
            listOf("hola", " hola "),
            listOf(" hola ", "hola"),
            listOf("      ", "hola"),
            listOf("hola", "   "),
        )
    }

    fun equalsData(): List<List<String>> {
        return listOf(
            listOf("hola", "hola"),
            listOf("  hola", "  hola"),
            listOf("hola  ", "hola  "),
            listOf(" hola ", " hola "),
            listOf("  ", "  "),
        )
    }

    fun withoutData(): List<List<String>> {
        return listOf(
            listOf("hola", ""),
            listOf("", "hola"),
            listOf("", ""),
            listOf("", "  "),
            listOf("  ", ""),
        )
    }
}