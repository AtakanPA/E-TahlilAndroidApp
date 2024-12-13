package com.example.e_tahlil.pages.admin

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel
@Composable
fun HastaAraPage(adminViewModel: AdminViewModel, modifier: Modifier, authViewModel: AuthViewModel, navController: NavController) {

    var hastaAdi by remember { mutableStateOf("") }
    var hastaSoyadi by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") } // Hata mesajı için durum değişkeni

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

            Button(onClick = {
                if (hastaAdi.isBlank() || hastaSoyadi.isBlank()) {
                    // Eğer bir alan boşsa hata mesajını ayarla
                    errorMessage = "Hasta adı ve soyadı boş olamaz!"
                } else {
                    // Alanlar doluysa işlemi gerçekleştir
                    adminViewModel.HastaVeTahlilGetir(hastaAdi, hastaSoyadi)
                    navController.navigate("hastadetay")
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