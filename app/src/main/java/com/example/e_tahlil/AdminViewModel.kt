package com.example.e_tahlil

import android.util.Log
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
    fun HastaVeTahlilGetir(name: String, surname: String) {
        HastaAra(name, surname)
        hasta.observeForever { hasta ->
            hasta?.let {
                TahlilleriGetir(it.hastaId)
            }
        }
    }
    fun TahlilleriGetir(hastaId: String?) {
        if (hastaId.isNullOrEmpty()) {
            Log.e("Error", "Hasta ID boş veya null")
            return
        }

        firestore.collection("users")
            .document(hastaId)
            .collection("tahlil")
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val tahlilList = documents.toObjects(Tahlil::class.java)
                    _hasta.value = _hasta.value?.copy(tahlilList = tahlilList)
                }
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }
    fun TahlilEkle(hastaId: String, tahlil: Tahlil) {
        if (hastaId.isEmpty()) {
            Log.e("Error", "Hasta ID boş")
            return
        }

        firestore.collection("users")
            .document(hastaId)
            .collection("tahlil")
            .add(tahlil) // Tahlil nesnesini ekle
            .addOnSuccessListener { documentReference ->
                Log.d("TahlilEkle", "Tahlil başarıyla eklendi: ${documentReference.id}")
            }
            .addOnFailureListener { exception ->
                Log.e("TahlilEkle", "Tahlil ekleme başarısız", exception)
            }
    }
    fun HastaAra(name: String, surname: String) {
        firestore.collection("users")
            .whereEqualTo("name", name)
            .whereEqualTo("surname", surname)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val hastaGelen = documents.documents.first()
                    val hastaId = hastaGelen.id // Belge ID'sini alın
                    _hasta.value = hastaGelen.toObject(Hasta::class.java)?.copy(hastaId = hastaId)
                } else {
                    _hasta.value = null // No match found
                }
            }
            .addOnFailureListener { exception ->
                _hasta.value = null
                exception.printStackTrace()
            }
    }
}