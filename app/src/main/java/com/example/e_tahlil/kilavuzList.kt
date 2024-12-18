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
val iggAgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 1, isYears = false, minValue = 700.0, maxValue = 1300.0),
    AgeGroup(minAge = 1, maxAge = 4, isYears = false, minValue = 280.0, maxValue = 750.0),
    AgeGroup(minAge = 4, maxAge = 7, isYears = false, minValue = 200.0, maxValue = 1200.0),
    AgeGroup(minAge = 7, maxAge = 13, isYears = false, minValue = 300.0, maxValue = 1500.0),
    AgeGroup(minAge = 13, maxAge = 36, isYears = false, minValue = 400.0, maxValue = 1300.0),
    AgeGroup(minAge = 36, maxAge = 72, isYears = true, minValue = 600.0, maxValue = 1500.0),
    AgeGroup(minAge = 72, maxAge = Int.MAX_VALUE, isYears = true, minValue = 639.0, maxValue = 1344.0)
)
val igaAgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 1, isYears = false, minValue = 0.0, maxValue = 11.0),
    AgeGroup(minAge = 1, maxAge = 4, isYears = false, minValue = 6.0, maxValue = 50.0),
    AgeGroup(minAge = 4, maxAge = 7, isYears = false, minValue = 8.0, maxValue = 90.0),
    AgeGroup(minAge = 7, maxAge = 13, isYears = false, minValue = 16.0, maxValue = 100.0),
    AgeGroup(minAge = 13, maxAge = 36, isYears = false, minValue = 20.0, maxValue = 230.0),
    AgeGroup(minAge = 36, maxAge = 72, isYears = true, minValue = 50.0, maxValue = 150.0),
    AgeGroup(minAge = 72, maxAge = Int.MAX_VALUE, isYears = true, minValue = 70.0, maxValue = 312.0)
)
val igmAgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 1, isYears = false, minValue = 5.0, maxValue = 30.0),
    AgeGroup(minAge = 1, maxAge = 4, isYears = false, minValue = 15.0, maxValue = 70.0),
    AgeGroup(minAge = 4, maxAge = 7, isYears = false, minValue = 10.0, maxValue = 90.0),
    AgeGroup(minAge = 7, maxAge = 13, isYears = false, minValue = 25.0, maxValue = 115.0),
    AgeGroup(minAge = 13, maxAge = 36, isYears = false, minValue = 30.0, maxValue = 120.0),
    AgeGroup(minAge = 36, maxAge = 72, isYears = true, minValue = 22.0, maxValue = 100.0),
    AgeGroup(minAge = 72, maxAge = Int.MAX_VALUE, isYears = true, minValue = 56.0, maxValue = 352.0)
)
val igg1AgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 3, isYears = false, minValue = 218.0, maxValue = 496.0),
    AgeGroup(minAge = 3, maxAge = 6, isYears = false, minValue = 143.0, maxValue = 394.0),
    AgeGroup(minAge = 6, maxAge = 9, isYears = false, minValue = 190.0, maxValue = 388.0),
    AgeGroup(minAge = 9, maxAge = 24, isYears = false, minValue = 286.0, maxValue = 680.0),
    AgeGroup(minAge = 24, maxAge = 48, isYears = true, minValue = 381.0, maxValue = 884.0),
    AgeGroup(minAge = 48, maxAge = 72, isYears = true, minValue = 292.0, maxValue = 816.0),
    AgeGroup(minAge = 72, maxAge = 96, isYears = true, minValue = 422.0, maxValue = 802.0),
    AgeGroup(minAge = 96, maxAge = 120, isYears = true, minValue = 456.0, maxValue = 938.0),
    AgeGroup(minAge = 120, maxAge = 144, isYears = true, minValue = 456.0, maxValue = 952.0),
    AgeGroup(minAge = 144, maxAge = 168, isYears = true, minValue = 347.0, maxValue = 993.0),
    AgeGroup(minAge = 168, maxAge = Int.MAX_VALUE, isYears = true, minValue = 422.0, maxValue = 1292.0),
)
val igg2AgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 3, isYears = false, minValue = 40.0, maxValue = 167.0),
    AgeGroup(minAge = 3, maxAge = 6, isYears = false, minValue = 23.0, maxValue = 147.0),
    AgeGroup(minAge = 6, maxAge = 9, isYears = false, minValue = 37.0, maxValue = 60.0),
    AgeGroup(minAge = 9, maxAge = 24, isYears = false, minValue = 30.0, maxValue = 327.0),
    AgeGroup(minAge = 24, maxAge = 48, isYears = true, minValue = 70.0, maxValue = 443.0),
    AgeGroup(minAge = 48, maxAge = 72, isYears = true, minValue = 85.0, maxValue = 513.0),
    AgeGroup(minAge = 72, maxAge = 96, isYears = true, minValue = 113.0, maxValue = 480.0),
    AgeGroup(minAge = 96, maxAge = 120, isYears = true, minValue = 163.0, maxValue = 513.0),
    AgeGroup(minAge = 120, maxAge = 144, isYears = true, minValue = 147.0, maxValue = 493.0),
    AgeGroup(minAge = 144, maxAge = 168, isYears = true, minValue = 140.0, maxValue = 440.0),
    AgeGroup(minAge = 168, maxAge = Int.MAX_VALUE, isYears = true, minValue = 117.0, maxValue = 747.0),
)
val igg3AgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 3, isYears = false, minValue = 4.0, maxValue = 23.0),
    AgeGroup(minAge = 3, maxAge = 6, isYears = false, minValue = 4.0, maxValue = 100.0),
    AgeGroup(minAge = 6, maxAge = 9, isYears = false, minValue = 12.0, maxValue = 62.0),
    AgeGroup(minAge = 9, maxAge = 24, isYears = false, minValue = 13.0, maxValue = 82.0),
    AgeGroup(minAge = 24, maxAge = 48, isYears = true, minValue = 17.0, maxValue = 90.0),
    AgeGroup(minAge = 48, maxAge = 72, isYears = true, minValue = 8.0, maxValue = 111.0),
    AgeGroup(minAge = 72, maxAge = 96, isYears = true, minValue = 15.0, maxValue = 133.0),
    AgeGroup(minAge = 96, maxAge = 120, isYears = true, minValue = 26.0, maxValue = 113.0),
    AgeGroup(minAge = 120, maxAge = 144, isYears = true, minValue = 12.0, maxValue = 179.0),
    AgeGroup(minAge = 144, maxAge = 168, isYears = true, minValue = 23.0, maxValue = 117.0),
    AgeGroup(minAge = 168, maxAge = Int.MAX_VALUE, isYears = true, minValue = 41.0, maxValue = 129.0),
)
val igg4AgeGroups = listOf(
    AgeGroup(minAge = 0, maxAge = 3, isYears = false, minValue = 1.0, maxValue =47.0),
    AgeGroup(minAge = 3, maxAge = 6, isYears = false, minValue = 1.0, maxValue =120.0),
    AgeGroup(minAge = 6, maxAge = 9, isYears = false, minValue = 1.0, maxValue = 120.0),
    AgeGroup(minAge = 9, maxAge = 24, isYears = false, minValue = 1.0, maxValue = 120.0),
    AgeGroup(minAge = 24, maxAge = 48, isYears = true, minValue = 1.0, maxValue = 120.0),
    AgeGroup(minAge = 48, maxAge = 72, isYears = true, minValue = 2.0, maxValue = 120.0),
    AgeGroup(minAge = 72, maxAge = 96, isYears = true, minValue = 1.0, maxValue = 138.0),
    AgeGroup(minAge = 96, maxAge = 120, isYears = true, minValue = 1.0, maxValue = 95.0),
    AgeGroup(minAge = 120, maxAge = 144, isYears = true, minValue = 1.0, maxValue = 153.0),
    AgeGroup(minAge = 144, maxAge = 168, isYears = true, minValue = 1.0, maxValue = 143.0),
    AgeGroup(minAge = 168, maxAge = Int.MAX_VALUE, isYears = true, minValue = 10.0, maxValue = 67.0),
)
var guides=Guide(
    name = "kilavuz-cilv",
    listOf(
        Deger(name = "IgA", igaAgeGroups),
        Deger(name = "IgA1",),
        Deger(name = "IgA2",),
        Deger(name = "IgG", iggAgeGroups),
        Deger(name = "IgM",igmAgeGroups),
        Deger(name = "IgG1", igg1AgeGroups),
        Deger(name = "IgG2", igg2AgeGroups),
        Deger(name = "IgG3", igg3AgeGroups),
        Deger(name = "IgG4", igg4AgeGroups),



        )
)
