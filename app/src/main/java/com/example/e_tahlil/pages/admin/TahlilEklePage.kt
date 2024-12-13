package com.example.e_tahlil.pages.admin

import android.app.DatePickerDialog

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel
import com.example.e_tahlil.Tahlil
import com.google.firebase.Timestamp
import java.util.Calendar

@Composable
fun TahlilEklePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    adminViewModel: AdminViewModel
) {
    // Tahlil bilgileri için state
    var iga by remember { mutableStateOf("") }
    var igg by remember { mutableStateOf("") }
    var igg1 by remember { mutableStateOf("") }
    var igg2 by remember { mutableStateOf("") }
    var igg3 by remember { mutableStateOf("") }
    var igg4 by remember { mutableStateOf("") }
    var igm by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Timestamp.now()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val hasta = adminViewModel.hasta.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tahlil Ekle", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Tahlil değerleri için TextField bileşenleri
        OutlinedTextField(
            value = iga,
            onValueChange = { iga = it },
            label = { Text("IgA") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = igg,
            onValueChange = { igg = it },
            label = { Text("IgG") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = igg1,
            onValueChange = { igg1 = it },
            label = { Text("IgG1") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = igg2,
            onValueChange = { igg2 = it },
            label = { Text("IgG2") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = igg3,
            onValueChange = { igg3 = it },
            label = { Text("IgG3") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = igg4,
            onValueChange = { igg4 = it },
            label = { Text("IgG4") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = igm,
            onValueChange = { igm = it },
            label = { Text("IgM") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tarih seçici
        Button(onClick = { showDatePicker = true }, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(text = "Tarih Seç")
        }
/*
        if (showDatePicker) {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    if (selectedCalendar.after(Calendar.getInstance())) {
                        Toast.makeText(context, "İleri bir tarih seçemezsiniz!", Toast.LENGTH_SHORT).show()
                    } else {
                        selectedDate = Timestamp(selectedCalendar.time)
                    }
                    showDatePicker = false
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }
*/
        // Tahlil Ekle Butonu
        Button(
            onClick = {
                val tahlil = Tahlil(
                    IgA = iga,
                    IgG = igg,
                    IgG1 = igg1,
                    IgG2 = igg2,
                    IgG3 = igg3,
                    IgG4 = igg4,
                    IgM = igm,
                    date = selectedDate
                )

                if (isAnyFieldFilled(tahlil)) {
                    hasta.value?.hastaId?.let { adminViewModel.TahlilEkle(it, tahlil) }
                    navController.navigate("hastadetay")
                } else {
                    errorMessage = "En az bir tahlil değeri doldurulmalı!"
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Tahlil Ekle")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hata mesajını göster
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Button(onClick = { navController.navigate("hastadetay") }, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text("İptal Et")
        }
    }
}

// En az bir alanın dolu olup olmadığını kontrol eden fonksiyon
fun isAnyFieldFilled(tahlil: Tahlil): Boolean {
    return tahlil.IgA.isNotEmpty() ||
            tahlil.IgG.isNotEmpty() ||
            tahlil.IgG1.isNotEmpty() ||
            tahlil.IgG2.isNotEmpty() ||
            tahlil.IgG3.isNotEmpty() ||
            tahlil.IgG4.isNotEmpty() ||
            tahlil.IgM.isNotEmpty()
}
