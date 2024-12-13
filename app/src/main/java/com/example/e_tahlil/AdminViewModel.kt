package com.example.e_tahlil

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AdminViewModel : ViewModel() {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _hasta = MutableLiveData<Hasta?>()
    val hasta: MutableLiveData<Hasta?> = _hasta

    fun HastaVeTahlilGetir(name: String, surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val hasta = HastaAra(name, surname)
            if (hasta != null) {
                val tahlilList = TahlilleriGetir(hasta.hastaId)
                _hasta.postValue(hasta.copy(tahlilList = tahlilList))
            } else {
                _hasta.postValue(null)
            }
        }
    }

    private suspend fun HastaAra(name: String, surname: String): Hasta? {
        return try {
            val documents = firestore.collection("users")
                .whereEqualTo("name", name)
                .whereEqualTo("surname", surname)
                .get()
                .await()

            if (!documents.isEmpty) {
                val document = documents.documents.first()
                val hastaId = document.id
                document.toObject(Hasta::class.java)?.copy(hastaId = hastaId)
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("HastaAra", "Hata: ${e.message}", e)
            null
        }
    }

    private suspend fun TahlilleriGetir(hastaId: String?): List<Tahlil>? {
        if (hastaId.isNullOrEmpty()) {
            Log.e("TahlilleriGetir", "Hasta ID boş veya null")
            return null
        }

        return try {
            val documents = firestore.collection("users")
                .document(hastaId)
                .collection("tahlil")
                .get()
                .await()

            if (!documents.isEmpty) {
                documents.toObjects(Tahlil::class.java)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("TahlilleriGetir", "Hata: ${e.message}", e)
            null
        }
    }

    fun KilavuzEkle(guide: Guide) {
        viewModelScope.launch(Dispatchers.IO) {
            if (guide.name.isEmpty() || guide.ageGroups.isEmpty()) {
                Log.e("KilavuzEkle", "Kılavuz adı veya yaş grubu boş")
                return@launch
            }

            try {
                firestore.collection("kilavuzlar").add(guide).await()
                Log.d("KilavuzEkle", "Kılavuz başarıyla eklendi")
            } catch (e: Exception) {
                Log.e("KilavuzEkle", "Kılavuz ekleme başarısız: ${e.message}", e)
            }
        }
    }

    fun TahlilEkle(hastaId: String, tahlil: Tahlil) {
        viewModelScope.launch(Dispatchers.IO) {
            if (hastaId.isEmpty() || isTahlilEmpty(tahlil)) {
                Log.e("TahlilEkle", "Hasta ID boş veya tahlil verileri eksik")
                return@launch
            }

            try {
                firestore.collection("users")
                    .document(hastaId)
                    .collection("tahlil")
                    .add(tahlil)
                    .await()

                Log.d("TahlilEkle", "Tahlil başarıyla eklendi")
            } catch (e: Exception) {
                Log.e("TahlilEkle", "Tahlil ekleme başarısız: ${e.message}", e)
            }
        }
    }

    private fun isTahlilEmpty(tahlil: Tahlil): Boolean {
        return tahlil.IgA.isEmpty() &&
                tahlil.IgG.isEmpty() &&
                tahlil.IgG1.isEmpty() &&
                tahlil.IgG2.isEmpty() &&
                tahlil.IgG3.isEmpty() &&
                tahlil.IgG4.isEmpty() &&
                tahlil.IgM.isEmpty()
    }
}
