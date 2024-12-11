package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel

@Composable
fun HastaDetayPage(navController: NavController, authViewModel: AuthViewModel, adminViewModel: AdminViewModel, modifier: Modifier) {


    val hasta = adminViewModel.hasta.observeAsState()

    Column(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hasta Temel Bilgileri
            Text("Hasta Adı: ${hasta.value?.name}")
            Text("Hasta Soyadı: ${hasta.value?.surname}")
            Text("Hasta Yaşı: ${hasta.value?.age}")

            Spacer(modifier = Modifier.height(16.dp)) // Biraz boşluk bırak

            // Tahlil Listesi Başlığı
            Text("Tahlil Detayları", style = MaterialTheme.typography.h6)

            // Tahlil Listesi
            if (hasta.value?.tahlilList.isNullOrEmpty()) {
                Text("Henüz tahlil eklenmedi.")
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()), // Uzun listeyi kaydırılabilir yap
                    horizontalAlignment = Alignment.Start
                ) {
                    hasta.value?.tahlilList?.forEach { tahlil ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = 4.dp
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("IgA: ${tahlil.IgA}")
                                Text("IgG: ${tahlil.IgG}")
                                Text("IgM: ${tahlil.IgM}")
                                Text("Tahlil Tarihi: ${tahlil.date.toDate()}")
                            }
                        }
                    }
                }
            }

            // Tahlil Ekle Butonu
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {
                        navController.navigate("tahlilekle")
                        },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text("Tahlil Ekle")
                }
            }
        }
    }

}