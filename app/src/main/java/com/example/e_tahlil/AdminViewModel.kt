package com.example.e_tahlil

import android.util.Log
import androidx.lifecycle.LiveData
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
    private val _kilavuzlar = MutableLiveData<List<Guide>?>()
    val kilavuzlar: LiveData<List<Guide>?> = _kilavuzlar

    init {
        KilavuzlariGercekZamanliDinle()
    }
    fun KilavuzlariGercekZamanliDinle() {
        firestore.collection("kilavuzlar").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("KilavuzlariDinle", "Dinleme hatası: ${e.message}", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                val guides = snapshot.toObjects(Guide::class.java)
                _kilavuzlar.postValue(guides) // LiveData'yı güncelliyoruz
            } else {
                _kilavuzlar.postValue(emptyList()) // Boş bir liste gönder
            }
        }
    }
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
    private suspend fun KilavuzlariGetir():List<Guide>? {

        return try {

            val documents=firestore.collection("kilavuzlar").get().await()
            if(!documents.isEmpty){


                documents.toObjects(Guide::class.java)
            }else{
                emptyList()

            }

        }catch (e: Exception) {
            Log.e("Kilavuzlari Getir", "Hata: ${e.message}", e)
            null
        }

    }
    fun KilavuzEkle(guide: Guide) {
        viewModelScope.launch(Dispatchers.IO) {
            if (guide.name.isEmpty() || guide.degerler.isEmpty()) {
                Log.e("KilavuzEkle", "Kılavuz adı veya yaş grubu boş")
                return@launch
            }

            try {
                firestore.collection("kilavuzlar")
                    .add(guide)
                    .addOnSuccessListener {
                        Log.d("KilavuzEkle", "Kılavuz başarıyla eklendi: ${it.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.e("KilavuzEkle", "Kılavuz ekleme başarısız: ${guide.name}", e)
                    }
                val currentKilavuzlar = _kilavuzlar.value?.toMutableList() ?: mutableListOf()
                currentKilavuzlar.add(guide)  // Yeni kılavuzu ekliyoruz
                _kilavuzlar.postValue(currentKilavuzlar)  //
                Log.d("KilavuzEkle", "Kılavuz başarıyla eklendi")
            } catch (e: Exception) {
                Log.e("KilavuzEkle", "Kılavuz ekleme başarısız: ${e.message}", e)
            }
        }
    }


}
