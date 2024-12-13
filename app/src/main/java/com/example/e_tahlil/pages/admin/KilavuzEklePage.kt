import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.e_tahlil.AdminViewModel
import com.example.e_tahlil.AgeGroup
import com.example.e_tahlil.AuthViewModel
import com.example.e_tahlil.Deger
import com.example.e_tahlil.Guide

@Composable
fun KilavuzEklePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    adminViewModel: AdminViewModel
) {
    var isAddingAgeGroup by remember { mutableStateOf(false) }
    var kilavuzName by remember { mutableStateOf("") }

    val predefinedDegerler = remember { mutableStateListOf(
        Deger("IgA", emptyList()),
        Deger("IgA1", emptyList()),
        Deger("IgA2", emptyList()),
        Deger("IgM", emptyList()),
        Deger("IgG", emptyList()),
        Deger("IgG1", emptyList()),
        Deger("IgG2", emptyList()),
        Deger("IgG3", emptyList()),
        Deger("IgG4", emptyList())
    )}
    var selectedDeger by remember { mutableStateOf(predefinedDegerler.first()) }


    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Text
        Text(
            text = "Kılavuz Ekle",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Kılavuz Name Input
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = kilavuzName,
            onValueChange = { kilavuzName = it },
            label = { Text("Kılavuz İsmi") }
        )

        // Add Kılavuz Button
        Button(
            onClick = {
                adminViewModel.KilavuzEkle(Guide(kilavuzName, predefinedDegerler))
                navController.navigate("adminhome")
            },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(0.5f)
        ) {
            Text("Kılavuzu Ekle")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Değerler",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(predefinedDegerler) { deger ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, MaterialTheme.shapes.medium)
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = deger.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Button(onClick = {
                            selectedDeger = deger
                            isAddingAgeGroup = true
                        }) {
                            Text("Yaş Grubu Ekle")
                        }
                    }

                    deger.ageGroups.forEachIndexed { index, ageGroup ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(text=if(ageGroup.isYears) {"${ageGroup.minAge/12}-${ageGroup.maxAge/12} yaş aralığı"} else {"${ageGroup.minAge}-${ageGroup.maxAge} ay aralığı"})
                            TextButton(onClick = {
                                val updatedAgeGroups = deger.ageGroups.toMutableList()
                                updatedAgeGroups.removeAt(index)
                                val updatedDeger = deger.copy(ageGroups = updatedAgeGroups)
                                val indexInList = predefinedDegerler.indexOf(deger)
                                if (indexInList != -1) {
                                    predefinedDegerler[indexInList] = updatedDeger
                                }
                            }) {
                                Text("Sil")
                            }

                        }
                    }
                }
            }
        }

        if (isAddingAgeGroup) {
            AddAgeGroupDialog(onDismiss = { isAddingAgeGroup = false }, onAdd = { newAgeGroup ->
                val updatedAgeGroups = selectedDeger.ageGroups.toMutableList()
                updatedAgeGroups.add(newAgeGroup)
                val updatedDeger = selectedDeger.copy(ageGroups = updatedAgeGroups)
                val index = predefinedDegerler.indexOfFirst { it.name == selectedDeger.name }
                if (index != -1) {
                    predefinedDegerler[index] = updatedDeger
                }
                isAddingAgeGroup = false
            })
        }
    }
}



@Composable
fun AddAgeGroupDialog(onDismiss: () -> Unit, onAdd: (AgeGroup) -> Unit) {
    var minAge by remember { mutableStateOf("") }
    var maxAge by remember { mutableStateOf("") }
    var minValue by remember { mutableStateOf("") }
    var maxValue by remember { mutableStateOf("") }
    var geometricMean by remember { mutableStateOf("") }
    var geometricSD by remember { mutableStateOf("") }
    var arithmeticMean by remember { mutableStateOf("") }
    var arithmeticSD by remember { mutableStateOf("") }
    var confidenceMin by remember { mutableStateOf("") }
    var confidenceMax by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }
    var isYears by remember { mutableStateOf(true) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Yaş Grubu Ekle") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = Color.Red, fontSize = 14.sp)
                }
                OutlinedTextField(
                    value = minAge,
                    onValueChange = { minAge = it },
                    label = { Text("Min Yaş") }
                )
                OutlinedTextField(
                    value = maxAge,
                    onValueChange = { maxAge = it },
                    label = { Text("Max Yaş") }
                )
                Row (){
                    Text("Yaş Ölçüsü",modifier = Modifier.padding(end = 8.dp))

                    Button(
                        onClick = { isYears = false },
                        colors = if (!isYears) {
                            ButtonDefaults.buttonColors() // Use default colors for selected state
                        } else {
                            ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black) // Custom colors for unselected state
                        }
                    ) {
                        Text("Ay")
                    }
                    Spacer(Modifier.padding(16.dp))
                    Button(
                        onClick = { isYears = true },
                        colors =  if (isYears) {
                            ButtonDefaults.buttonColors() // Use default colors for selected state
                        } else {
                            ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black) // Custom colors for unselected state
                        }
                    ) {
                        Text("Yıl")
                    }

                }
                OutlinedTextField(
                    value = minValue,
                    onValueChange = { minValue = it },
                    label = { Text("Min Değer") }
                )
                OutlinedTextField(
                    value = maxValue,
                    onValueChange = { maxValue = it },
                    label = { Text("Max Değer") }
                )
                OutlinedTextField(
                    value = geometricMean,
                    onValueChange = { geometricMean = it },
                    label = { Text("Geometrik Ort") }
                )
                OutlinedTextField(
                    value = geometricSD,
                    onValueChange = { geometricSD = it },
                    label = { Text("Geometrik SD") }
                )
                OutlinedTextField(
                    value = arithmeticMean,
                    onValueChange = { arithmeticMean = it },
                    label = { Text("Aritmetik Ort") }
                )
                OutlinedTextField(
                    value = arithmeticSD,
                    onValueChange = { arithmeticSD = it },
                    label = { Text("Aritmetik SD") }
                )
                OutlinedTextField(
                    value = confidenceMin,
                    onValueChange = { confidenceMin = it },
                    label = { Text("Güven Min") }
                )
                OutlinedTextField(
                    value = confidenceMax,
                    onValueChange = { confidenceMax = it },
                    label = { Text("Güven Max") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {

                try {
                   val yearstomonthmin=if(isYears) minAge.toInt().times(12) else minAge.toInt()
                    val yearstomonthmax=if(isYears) maxAge.toIntOrNull()?.times(12) else maxAge.toIntOrNull()
                    val newAgeGroup = AgeGroup(

                        yearstomonthmin,
                        yearstomonthmax?:1800,
                        isYears = isYears,
                        minValue.toDouble(),
                        maxValue.toDouble(),
                        geometricMean.toDoubleOrNull() ?: 0.0,
                        geometricSD.toDoubleOrNull() ?: 0.0,
                        arithmeticMean.toDoubleOrNull() ?: 0.0,
                        arithmeticSD.toDoubleOrNull() ?: 0.0,
                        confidenceMin.toDoubleOrNull() ?: 0.0,
                        confidenceMax.toDoubleOrNull() ?: 0.0
                    )
                    onAdd(newAgeGroup)
                } catch (e: Exception) {
                    errorMessage = if(minAge.isEmpty()||!minAge.isDigitsOnly()) {
                        "Lütfen Yaş Alanlarını Doldurunuz"
                    } else if(minValue.isEmpty()||maxValue.isEmpty()) {
                        "Lütfen Minimum ve Maksimum Değer Alanlarını Doldurunuz"
                    }else
                        "Lütfen Geçerli Değerler Girin"
                }
            }) {
                Text("Ekle")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}





