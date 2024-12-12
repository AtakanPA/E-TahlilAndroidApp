package com.example.e_tahlil


data class GuideValues(
    val igG: String = "",
    val igG1: String = "",
    val igG2: String = "",
    val igG3: String = "",
    val igG4: String = "",
    val igA: String = "",
    val igA1: String = "",
    val igA2: String = "",
    val igM: String = ""
)

data class AgeGroup(
    val range: String, // Örnek: "0-1 ay", "1-5 yıl"
    val values: GuideValues
)

data class Guide(
    val name: String, // Örnek: "Kılavuz 1", "Kılavuz 2"
    val ageGroups: List<AgeGroup>
)

data class GuideRepository(
    val guides: List<Guide>
)