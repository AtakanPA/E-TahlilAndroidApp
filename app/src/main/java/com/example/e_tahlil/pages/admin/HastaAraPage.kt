package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel

@Composable
fun HastaAraPage(adminViewModel: AdminViewModel, modifier: Modifier, authViewModel: AuthViewModel, navController: NavController) {

    var hastaAdi by remember { mutableStateOf("") }
    var hastaSoyadi by remember { mutableStateOf("") }
    Column(modifier = modifier) {

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            OutlinedTextField(value=hastaAdi, label = { Text("Hasta Adı") }, onValueChange = {hastaAdi=it})
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = hastaSoyadi,label = { Text("Hasta Soyadı")}, onValueChange = {hastaSoyadi=it})
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {adminViewModel.HastaVeTahlilGetir(hastaAdi,hastaSoyadi)
            navController.navigate("hastadetay")

            }){
                Text("Hasta Ara")

            }
        }
    }
}