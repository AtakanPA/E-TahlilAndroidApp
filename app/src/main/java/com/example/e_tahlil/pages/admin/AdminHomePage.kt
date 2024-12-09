package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.e_tahlil.AuthState
import com.example.e_tahlil.AuthViewModel

@Composable
fun AdminHomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {


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


    Column {


        Column {
            Button(onClick = { authViewModel.signout() }) { }

        }
        Text("Admin Home Page")
    }

}