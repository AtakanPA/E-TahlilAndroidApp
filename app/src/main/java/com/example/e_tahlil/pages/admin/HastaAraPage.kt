package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel

@Composable
fun HastaAraPage(viewModel: AdminViewModel, modifier: Modifier, authViewModel: AuthViewModel, navController: NavController) {
    val hasta by viewModel.hasta.observeAsState()

    Column {
        if (hasta != null) {
            Text("Hasta: ${hasta?.name} ${hasta?.surname}")
            Text("Age: ${hasta?.age}")
            Text("Tahlil List: ${hasta?.tahlilList?.joinToString { it.toString() }}")
        } else {
            Text("Hasta not found")
        }
    }
}