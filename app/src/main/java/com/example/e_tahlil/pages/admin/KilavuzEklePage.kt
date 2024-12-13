package com.example.e_tahlil.pages.admin

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card

import androidx.compose.material.MaterialTheme

import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AgeGroup
import com.example.e_tahlil.AgeUnit

import com.example.e_tahlil.AuthViewModel
import com.example.e_tahlil.Guide
import com.example.e_tahlil.GuideValues
@Composable
fun KilavuzEklePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    adminViewModel: AdminViewModel
) {
    var isVisible by remember { mutableStateOf(false) }
    val ageGroups = remember { mutableStateListOf<AgeGroup>() } // Yaş grubu listesi
    var kilavuzName by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Başlık

        // "Yaş Grubu Ekle" Butonu
        if (!isVisible) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = kilavuzName, // MutableState'in içindeki değeri kullan
                onValueChange = { kilavuzName = it }, // MutableState'in değerini güncelle
                label = { Text("Kılavuz İsmi") }
            )
            Button(onClick = {
                val guide = Guide(kilavuzName.toString(), ageGroups)
                adminViewModel.KilavuzEkle(guide)
                navController.navigate("adminhome")


            }, modifier = Modifier.padding(8.dp).fillMaxWidth(0.5f)) {
                Text("Kılavuzu Ekle")
            }
            Button(
                onClick = { isVisible = true },
                modifier = Modifier.padding(8.dp).fillMaxWidth(0.5f)
            ) {
                Text("Yaş Grubu Ekle")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Eklenen Yaş Grupları Listesi
            if (ageGroups.isNotEmpty()) {
                Text("Eklenen Yaş Grupları:", style = MaterialTheme.typography.h6)
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(ageGroups) { ageGroup ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 4.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "Yaş Grubu: ${ageGroup.minAge}-${ageGroup.maxAge} ${ageGroup.ageUnit}",
                                    style = MaterialTheme.typography.body1
                                )
                                Text("IgG: ${ageGroup.values.igGMin}-${ageGroup.values.igGMax}")
                                Text("IgA: ${ageGroup.values.igAMin}-${ageGroup.values.igAMax}")
                                Text("IgM: ${ageGroup.values.igMMin}-${ageGroup.values.igMMax}")
                                Text("IgG1: ${ageGroup.values.igG1Min}-${ageGroup.values.igG1Max}")
                                Text("IgG2: ${ageGroup.values.igG2Min}-${ageGroup.values.igG2Max}")
                                Text("IgG3: ${ageGroup.values.igG3Min}-${ageGroup.values.igG3Max}")
                                Text("IgG4: ${ageGroup.values.igG4Min}-${ageGroup.values.igG4Max}")

                            }
                        }
                    }
                }
            } else {
                Text("Henüz yaş grubu eklenmedi.", style = MaterialTheme.typography.body2)
            }


        }

        // Modal görünümü
        YasGrubuEkleme(
            visible = isVisible,
            onClose = { isVisible = false },
            onSave = { newAgeGroup ->
                ageGroups.add(newAgeGroup) // Yeni yaş grubunu listeye ekle
                isVisible = false // Modalı kapat
            }
        )
    }
}


@Composable
fun YasGrubuEkleme(
    visible: Boolean,
    onClose: () -> Unit,
    onSave: (AgeGroup) -> Unit // Yeni yaş grubunu kaydetmek için lambda
) {
    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            val scrollState = rememberScrollState()
            var minAge by remember { mutableStateOf("") }
            var maxAge by remember { mutableStateOf("") }
            var selectedUnit by remember { mutableStateOf(AgeUnit.YEAR) }
            var igGMin by remember { mutableStateOf("") }
            var igGMax by remember { mutableStateOf("") }
            var igAMin by remember { mutableStateOf("") }
            var igAMax by remember { mutableStateOf("") }
            var igMMin by remember { mutableStateOf("") }
            var igMMax by remember { mutableStateOf("") }
            var igG1Min by remember { mutableStateOf("") }
            var igG1Max by remember { mutableStateOf("") }
            var igG2Min by remember { mutableStateOf("") }
            var igG2Max by remember { mutableStateOf("") }
            var igG3Min by remember { mutableStateOf("") }
            var igG3Max by remember { mutableStateOf("") }
            var igG4Min by remember { mutableStateOf("") }
            var igG4Max by remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(bottom = 80.dp), // Butonlar için yer bırak
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Form Başlığı
                    Text(
                        text = "Yeni Yaş Grubu Ekle",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        //Yaş girdisi
                        Text(
                            text = "Yaş Aralığı",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = minAge,
                                onValueChange = { minAge = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = maxAge,
                                onValueChange = { maxAge = it },
                                label = { Text("Max") }
                            )
                        }

                        Text(
                            text = "Yaş Birimi",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Button(
                                onClick = { selectedUnit = AgeUnit.YEAR },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (selectedUnit == AgeUnit.YEAR) MaterialTheme.colors.primary else MaterialTheme.colors.surface
                                )
                            ) {
                                Text("Yıl")
                            }
                            Button(
                                onClick = { selectedUnit = AgeUnit.MONTH },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (selectedUnit == AgeUnit.MONTH) MaterialTheme.colors.primary else MaterialTheme.colors.surface
                                )
                            ) {
                                Text("Ay")
                            }
                        }

                        // IgG, IgA, IgM Değerleri
                        // IgA Başlık ve Değerleri
                        Text(
                            text = "IgA",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igAMin,
                                onValueChange = { igAMin = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igAMax,
                                onValueChange = { igAMax = it },
                                label = { Text("Max") }
                            )
                        }     // IgA Başlık ve Değerleri
                        Text(
                            text = "IgM",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igMMin,
                                onValueChange = { igMMin = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igMMax,
                                onValueChange = { igMMax = it },
                                label = { Text("Max") }
                            )
                        }
                        // IgA Başlık ve Değerleri
                        Text(
                            text = "IgG",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igGMin,
                                onValueChange = { igGMin = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igGMax,
                                onValueChange = { igGMax = it },
                                label = { Text("Max") }
                            )
                        }
                        // IgA Başlık ve Değerleri
                        Text(
                            text = "IgG1",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG1Min,
                                onValueChange = { igG1Min = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG1Max,
                                onValueChange = { igG1Max = it },
                                label = { Text("Max") }
                            )
                        }
                        // IgA Başlık ve Değerleri
                        Text(
                            text = "IgG2",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG2Min,
                                onValueChange = { igG2Min = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG2Max,
                                onValueChange = { igG2Max = it },
                                label = { Text("Max") }
                            )
                        }
                        // IgA Başlık ve Değerleri
                        Text(
                            text = "IgG3",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG3Min,
                                onValueChange = { igG3Min = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG3Max,
                                onValueChange = { igG3Max = it },
                                label = { Text("Max") }
                            )
                        }
                        // IgA Başlık ve Değerleri
                        Text(
                            text = "IgG4",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG4Min,
                                onValueChange = { igG4Min = it },
                                label = { Text("Min") }
                            )
                            OutlinedTextField(
                                modifier = Modifier.weight(1f),
                                value = igG4Max,
                                onValueChange = { igG4Max = it },
                                label = { Text("Max") }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                val newAgeGroup = AgeGroup(
                                    minAge = minAge.toIntOrNull() ?: 0,
                                    maxAge = maxAge.toIntOrNull() ?: 0,
                                    ageUnit = selectedUnit,
                                    values = GuideValues(
                                        igGMin = igGMin.toDoubleOrNull() ?: 0.0,
                                        igGMax = igGMax.toDoubleOrNull() ?: 0.0,
                                        igAMin = igAMin.toDoubleOrNull() ?: 0.0,
                                        igAMax = igAMax.toDoubleOrNull() ?: 0.0,
                                        igMMin = igMMin.toDoubleOrNull() ?: 0.0,
                                        igMMax = igMMax.toDoubleOrNull() ?: 0.0,
                                        igG1Min = igG1Min.toDoubleOrNull() ?: 0.0,
                                        igG1Max = igG1Max.toDoubleOrNull() ?: 0.0,
                                        igG2Min = igG2Min.toDoubleOrNull() ?: 0.0,
                                        igG2Max = igG2Max.toDoubleOrNull() ?: 0.0,
                                        igG3Min = igG3Min.toDoubleOrNull() ?: 0.0,
                                        igG3Max = igG3Max.toDoubleOrNull() ?: 0.0,
                                        igG4Min = igG4Min.toDoubleOrNull() ?: 0.0,
                                        igG4Max = igG4Max.toDoubleOrNull() ?: 0.0,
                                    )
                                )
                                onSave(newAgeGroup) // Save the new age group
                            },
                            modifier = Modifier.weight(0.5f)
                        ) {
                            Text("Kaydet")
                        }
                        Spacer(Modifier.padding(8.dp))
                        Button(
                            onClick = { onClose() },
                            modifier = Modifier.weight(0.5f)
                        ) {
                            Text("İptal")
                        }
                    }
                }




        }
    }
}