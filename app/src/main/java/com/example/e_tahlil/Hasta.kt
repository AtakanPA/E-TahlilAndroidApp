package com.example.e_tahlil

data class Hasta(
    var hastaId: String = "",
    var name: String = "",
    var surname: String = "",
    var age: Int = 0,
    var tahlilList: List<Tahlil> = emptyList()
)
