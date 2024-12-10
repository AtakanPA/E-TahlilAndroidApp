package com.example.e_tahlil

import com.google.firebase.Timestamp

data class Tahlil(
    val hastaId:String,
    val IgA:Double,
    val IgM:Double,
    val IgG:Double,
    val IgG1:Double,
    val IgG2:Double,
    val IgG3:Double,
    val IgG4:Double,
    val date: Timestamp,
    val tahlilId:String

    )
