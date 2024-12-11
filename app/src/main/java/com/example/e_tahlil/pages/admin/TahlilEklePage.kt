package com.example.e_tahlil.pages.admin

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel
import com.example.e_tahlil.Tahlil
import com.google.firebase.Timestamp
import java.util.Calendar


@Composable
fun TahlilEklePage(modifer: Modifier, navController: NavController, authViewModel: AuthViewModel, adminViewModel: AdminViewModel){

    // Tahlil bilgileri için state
    var iga by remember { mutableStateOf("") }
    var igg by remember { mutableStateOf("") }
    var igg1 by remember { mutableStateOf("") }
    var igg2 by remember { mutableStateOf("") }
    var igg3 by remember { mutableStateOf("") }
    var igg4 by remember { mutableStateOf("") }
    var igm by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Timestamp.now()) } // Varsayılan şu anki zaman
    var showDatePicker by remember { mutableStateOf(false) } // Tarih seçici için dialog kontrolü
    val context = LocalContext.current
    val hasta=adminViewModel.hasta.observeAsState();
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tahlil Ekle", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        // Tahlil değerleri için TextField bileşenleri
        TextField(value = iga, onValueChange = { iga = it }, label = { Text("IgA") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = igg, onValueChange = { igg = it }, label = { Text("IgG") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = igg1, onValueChange = { igg1 = it }, label = { Text("IgG1") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = igg2, onValueChange = { igg2 = it }, label = { Text("IgG2") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = igg3, onValueChange = { igg3 = it }, label = { Text("IgG3") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = igg4, onValueChange = { igg4 = it }, label = { Text("IgG4") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = igm, onValueChange = { igm = it }, label = { Text("IgM") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        // Tarih seçici
        Button(onClick = { showDatePicker = true }) {
            Text(text = "Tarih Seç")
        }

        if (showDatePicker) {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    val now = Calendar.getInstance()

                    // Tarih kontrolü (ileriki bir tarih girişi engelleniyor)
                    if (selectedCalendar.after(now)) {
                        Toast.makeText(context, "İleriki bir tarih seçemezsiniz!", Toast.LENGTH_SHORT).show()
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

        Spacer(modifier = Modifier.height(16.dp))

        // Tahlil Ekle Butonu
        Button(
            onClick = {
                // Tahlil objesi oluştur
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


                hasta.value?.hastaId?.let { adminViewModel.TahlilEkle(it, tahlil) }

                    navController.navigate("hastadetay")

            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Tahlil Ekle")
        }
    }








}