package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AuthViewModel

@Composable
fun DegerAraPage(modifier: Modifier = Modifier,
                 navController: NavController,
                 authViewModel: AuthViewModel,
                 adminViewModel: AdminViewModel,
                 dogumTarihi: String?){



    Column (modifier){ DropdownMenuExample()

        Text("asdasdasdsa")
    }





}

@Composable
fun DropdownMenuExample() {
    // Menü açık/kapalı durumunu kontrol etmek için bir state
    var expanded by remember { mutableStateOf(false) }

    // Seçilen öğeyi saklamak için bir state
    var selectedItem by remember { mutableStateOf("Seçim Yapılmadı") }

    // Menüdeki öğelerin listesi
    val menuItems = listOf("IgA", "IgM", "IgG","IgG1","IgG2","IgG3","IgG4")

    // Ana görünüm
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Seçilen: $selectedItem", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Butona tıklanıldığında menüyü aç/kapa
        Button(onClick = { expanded = true }) {
            Text("Menüyü Aç")
        }

        // DropdownMenu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Menü dışına tıklanınca kapat
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedItem = item // Seçilen öğeyi güncelle
                        expanded = false // Menü kapansın
                    }
                )
            }
        }
    }
}
