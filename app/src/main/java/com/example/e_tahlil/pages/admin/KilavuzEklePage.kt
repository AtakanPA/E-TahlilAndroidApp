package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AgeGroup
import com.example.e_tahlil.AuthViewModel
import com.example.e_tahlil.GuideValues

@Composable
fun KilavuzEklePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    adminViewModel: AdminViewModel
) {
    var isVisible by remember { mutableStateOf(false) }
   Column(modifier=modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


       Column(
           modifier = modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           if(!isVisible)
           Button(onClick = { isVisible = !isVisible }) {
               Text("Yaş Grubu Ekle")
           }

           Spacer(modifier = Modifier.height(16.dp))

           YasGrubuEkleme(visible = isVisible, onClose = {isVisible=false})
       }
    }

}
@Composable
fun YasGrubuEkleme(visible: Boolean, onClose: () -> Unit) {
    if (visible) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var ageRange by remember { mutableStateOf("") }
            var igG by remember { mutableStateOf("") }
            var igA by remember { mutableStateOf("") }
            var igM by remember { mutableStateOf("") }
            var igG1 by remember { mutableStateOf("") }
            var igG2 by remember { mutableStateOf("") }
            var igG3 by remember { mutableStateOf("") }
            var igG4 by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = ageRange,
                    onValueChange = { ageRange = it },
                    label = { Text("Yaş Grubu") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igA,
                    onValueChange = { igA = it },
                    label = { Text("IgA") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igM,
                    onValueChange = { igM = it },
                    label = { Text("IgM") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igG,
                    onValueChange = { igG = it },
                    label = { Text("IgG") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igG1,
                    onValueChange = { igG1 = it },
                    label = { Text("IgG1") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igG2,
                    onValueChange = { igG2 = it },
                    label = { Text("IgG2") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igG3,
                    onValueChange = { igG3 = it },
                    label = { Text("IgG3") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = igG4,
                    onValueChange = { igG4 = it },
                    label = { Text("IgG4") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // Kaydet işleminden sonra modal kapanır
                        onClose()
                    },
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text("Kaydet")
                }

                Button(
                    onClick = {
                        // İptal işleminden sonra modal kapanır
                        onClose()
                    },
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text("İptal")
                }
            }
        }
    }
}