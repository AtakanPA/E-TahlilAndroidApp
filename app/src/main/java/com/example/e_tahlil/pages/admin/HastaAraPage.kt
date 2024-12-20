package com.example.e_tahlil.pages.admin

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import java.util.Calendar
import java.util.Locale


@Composable
fun HastaAraPage(adminViewModel: AdminViewModel, modifier: Modifier, authViewModel: AuthViewModel, navController: NavController) {

    var hastaAdi by remember { mutableStateOf("") }
    var hastaSoyadi by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") } // Hata mesajı için durum değişkeni
    var selectedDate by remember { mutableStateOf("") } // Varsayılan şu anki zaman
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current // Şu anki bağlam
    Column(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = hastaAdi,
                label = { Text("Hasta Adı") },
                onValueChange = { hastaAdi = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = hastaSoyadi,
                label = { Text("Hasta Soyadı") },
                onValueChange = { hastaSoyadi = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier=Modifier.clickable { showDatePicker = true }) {   OutlinedTextField(
                enabled = false,
                value = selectedDate,
                label = { Text("Doğum Tarihi") },
                onValueChange = {  }
            )  }


            if (showDatePicker) {
                // Locale ayarını Türkçe'ye çevir
                val locale = Locale("tr", "TR")
                Locale.setDefault(locale)
                val config = context.resources.configuration
                config.setLocale(locale)
                context.createConfigurationContext(config)

                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedCalendar = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth)
                        }
                        val now = Calendar.getInstance()

                        // Tarih kontrolü (ileriki bir tarih engelleniyor)
                        if (selectedCalendar.after(now)) {
                            Toast.makeText(context, "İleriki bir tarih seçemezsiniz!", Toast.LENGTH_SHORT).show()
                        } else {
                            selectedDate = "${dayOfMonth}.${month + 1}.${year}"
                        }
                        showDatePicker = false
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (hastaAdi.isBlank() || hastaSoyadi.isBlank()||selectedDate.isBlank()) {
                    // Eğer bir alan boşsa hata mesajını ayarla
                    errorMessage = "Hasta adı, soyadı ve tarih boş olamaz!"
                } else {
                    // Alanlar doluysa işlemi gerçekleştir
                    adminViewModel.HastaVeTahlilGetir(hastaAdi, hastaSoyadi)
                    navController.navigate("hastadetay/$selectedDate")
                }
            }) {
                Text("Hasta Ara")
            }

            // Hata mesajını göster
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
fun showDatePicker(activity: FragmentActivity, onDateSelected: (String) -> Unit) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Tarih Seç")
        .build()

    datePicker.addOnPositiveButtonClickListener { selection ->
        val selectedDate = datePicker.headerText // Örneğin, "May 10, 2024"
        onDateSelected(selectedDate)
    }

    datePicker.show(activity.supportFragmentManager, "datePicker")
}