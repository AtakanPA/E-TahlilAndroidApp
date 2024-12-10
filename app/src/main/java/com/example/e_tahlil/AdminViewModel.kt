package com.example.e_tahlil

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class AdminViewModel: ViewModel(){

    private val firestore:FirebaseFirestore=FirebaseFirestore.getInstance()
    val _hasta= MutableLiveData<Hasta?>()
    val hasta:MutableLiveData<Hasta?> = _hasta
    fun TahlilEkle()
    {




    }
    fun HastaAra(name: String, surname: String) {
        firestore.collection("users")
            .whereEqualTo("name", name)
            .whereEqualTo("surname", surname)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val hastaGelen = documents.toObjects(Hasta::class.java)
                    if (hastaGelen.isNotEmpty()) {
                        // Assume there's only one matching result for simplicity
                        _hasta.value = hastaGelen.first()
                    }
                } else {
                    _hasta.value = null // No match found
                }
            }
            .addOnFailureListener { exception ->
                // Handle Firestore error
                _hasta.value = null
                exception.printStackTrace()
            }
    }
}