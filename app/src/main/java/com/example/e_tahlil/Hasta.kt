package com.example.e_tahlil

data class Hasta(
    var hastaId: String = "",
    var name: String = "",
    var surname: String = "",
    var age: String = "",
    var tahlilList: List<Tahlil> ?= emptyList()
)