package com.example.e_tahlil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_tahlil.pages.admin.AdminLayout
import com.example.e_tahlil.ui.theme.ETahlilTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val authViewModel:AuthViewModel by viewModels()
        val adminViewModel: AdminViewModel by viewModels()
        setContent {
            ETahlilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   AdminLayout(modifier = Modifier.padding(innerPadding),authViewModel,adminViewModel)
                }
            }
        }
    }
}


