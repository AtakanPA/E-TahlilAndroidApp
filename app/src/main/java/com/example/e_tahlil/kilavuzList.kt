package com.example.e_tahlil

data class AgeGroup(
    val minAge: Int = 0,            // Varsayılan değerler ekledik
    val maxAge: Int = 0,
    val isYears: Boolean = true,
    val minValue: Double = 0.0,
    val maxValue: Double = 0.0,
    val geometric_mean: Double = 0.0,
    val geometric_sd: Double = 0.0,
    val aritmethic_mean: Double = 0.0,
    val aritmethic_sd: Double = 0.0,
    val confidence_interval_min: Double = 0.0,
    val confidence_interval_max: Double = 0.0,
)

data class Deger(
    val name: String = "",
    val ageGroups: List<AgeGroup> = emptyList()
)

data class Guide(
    val name: String = "",
    val degerler: List<Deger> = emptyList()
)
