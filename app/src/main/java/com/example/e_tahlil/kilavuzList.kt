package com.example.e_tahlil

enum class AgeUnit {
    MONTH, // Ay
    YEAR   // Yıl
}

data class AgeGroup(
    val minAge: Int,            // Minimum yaş (integer)
    val maxAge: Int,            // Maksimum yaş (integer)
    val ageUnit: AgeUnit,       // Ay mı yıl mı olduğunu belirtir
    val values: GuideValues     // Referans değerleri
)

data class GuideValues(
    val igGMin: Double = 0.0,
    val igGMax: Double = 0.0,
    val igG1Min: Double = 0.0,
    val igG1Max: Double = 0.0,
    val igG2Min: Double = 0.0,
    val igG2Max: Double = 0.0,
    val igG3Min: Double = 0.0,
    val igG3Max: Double = 0.0,
    val igG4Min: Double = 0.0,
    val igG4Max: Double = 0.0,
    val igAMin: Double = 0.0,
    val igAMax: Double = 0.0,
    val igMMin: Double = 0.0,
    val igMMax: Double = 0.0
)

data class Guide(
    val name: String,             // Örnek: "Kılavuz 1", "Kılavuz 2"
    val ageGroups: List<AgeGroup> // Yaş grubu ve referans değerlerini içerir
)