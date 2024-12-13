package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
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
fun HastaDetayPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    adminViewModel: AdminViewModel,
    modifier: Modifier = Modifier
) {
    val hasta = adminViewModel.hasta.observeAsState()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hasta Adı: ${hasta.value?.name}")
        Text("Hasta Soyadı: ${hasta.value?.surname}")
        Text("Hasta Yaşı: ${hasta.value?.age}")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tahlil Detayları", style = MaterialTheme.typography.bodySmall)

        if (hasta.value?.tahlilList.isNullOrEmpty()) {
            Text("Henüz tahlil eklenmedi.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(hasta.value?.tahlilList ?: emptyList()) { tahlil ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("tahlilekle") },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Tahlil Ekle")
        }
    }
}