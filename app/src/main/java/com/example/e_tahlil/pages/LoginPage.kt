package com.example.e_tahlil.pages

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.e_tahlil.AuthState
import com.example.e_tahlil.AuthViewModel

@Composable
fun LoginPage(modifier: Modifier=Modifier,navController: NavController,authViewModel: AuthViewModel){

    var email by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val authState=authViewModel.authState.observeAsState()
    val context= LocalContext.current
    val userRole=authViewModel.userRole.observeAsState()
    LaunchedEffect(authState.value, userRole.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                if (userRole.value == "admin") {
                    navController.navigate("adminhome")
                } else if (userRole.value == "user") {
                    navController.navigate("home")
                } else {
                    // Rol alınmadıysa bekleyebilir veya hata gösterebilirsin
                    println("Rol bilgisi alınamadı.")
                }
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }
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

    Button(onClick ={authViewModel.login(email,password)},
        enabled = authState.value!=AuthState.Loading) {

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