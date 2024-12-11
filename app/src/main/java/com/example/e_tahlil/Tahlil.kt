package com.example.e_tahlil

import com.google.firebase.Timestamp

data class Tahlil(
    var IgA: String = "",
    var IgG: String = "",
    var IgG1: String = "",
    var IgG2: String = "",
    var IgG3: String = "",
    var IgG4: String = "",
    var IgM: String = "",
    var date: Timestamp = Timestamp.now() // Varsayılan olarak şu anki zamanı ayarla
)