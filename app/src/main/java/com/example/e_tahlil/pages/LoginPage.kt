package com.example.e_tahlil.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.e_tahlil.AuthViewModel

@Composable
fun LoginPage(modifier: Modifier=Modifier,navController: NavController,authViewModel: AuthViewModel){

    var email by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }
Column(modifier=Modifier.fillMaxSize(),
    verticalArrangement= Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {

    Text(text="Login Page",fontSize=32.sp)
    Spacer(modifier=Modifier.height(16.dp));
    OutlinedTextField(value = email, onValueChange = {
        email=it
    },
        label = {
            Text("Email")


        })
    Spacer(modifier=Modifier.height(16.dp));
    OutlinedTextField(value = password, onValueChange = {
        password=it
    },
        label = {
            Text("Şifre")


        },)
    Spacer(modifier=Modifier.height(16.dp));

    Button(onClick ={} ) {

        Text("Giriş Yap")
    }
    Spacer(modifier=Modifier.height(16.dp));

    TextButton(onClick = {
        navController.navigate(
            "signup"
        )


    }) {
    Text("Hesabınız yok mu, Kayıt Olun")


    }
}


}