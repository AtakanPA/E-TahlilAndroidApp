package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthState
import com.example.e_tahlil.AuthViewModel

@Composable
fun AdminHomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel,adminView: AdminViewModel) {


    val authState=authViewModel.authState.observeAsState()
    val userRole=authViewModel.userRole.observeAsState()
    LaunchedEffect(authState.value,userRole.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            is AuthState.Authenticated ->
                if (userRole.value.equals("user")) {
                    navController.navigate("home")
                }else
                {
                    Unit
                }
            else -> Unit
        }

    }
    val kilavuzlar by adminView.kilavuzlar.observeAsState(initial = emptyList())

    Column(modifier) {
        Column (modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ){


        Button(onClick ={
            navController.navigate("kilavuzekle")
        },modifier=Modifier.fillMaxWidth(0.5f)) {

            Text("Kılavız Ekle")


        }
           Button({
               navController.navigate("hastaara")
           },modifier=Modifier.fillMaxWidth(0.5f)) {


               Text("Hasta Ara")

           }

            LazyColumn {
                kilavuzlar?.let {
                    items(it.size) { index -> // Şimdi doğru çalışır
                        Text(text = "Kılavuz Adı: ${kilavuzlar?.get(index)?.name}")
                    }
                }
            }

        }

    }

}