package com.practica1Moviles.Ticona.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica1Moviles.Ticona.ui.theme.Practica1Moviles22200168Theme

@Composable
fun CalculadoraAguaScreen(onBack: () -> Unit) {
    val focusManager = LocalFocusManager.current

    var nombre by remember { mutableStateOf("") }
    var pesoText by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("Masculino") }

    val generos = listOf("Masculino", "Femenino", "Sin especificar")

    var resultadoText by remember { mutableStateOf<String?>(null) }
    var errorText by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Calculadora de Consumo de Agua", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = pesoText,
            onValueChange = { pesoText = it },
            label = { Text("Peso corporal (kg)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Género:")
        generos.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = genero == item,
                    onClick = { genero = item }
                )
                Text(text = item)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val peso = pesoText.toDoubleOrNull()
                if (nombre.isBlank()) {
                    errorText = "Por favor, ingresa tu nombre"
                    resultadoText = null
                    return@Button
                }
                if (peso == null || peso <= 0.0) {
                    errorText = "Ingresa un peso válido y positivo"
                    resultadoText = null
                    return@Button
                }

                val factor = when (genero) {
                    "Masculino" -> 1.02
                    "Femenino" -> 1.01
                    else -> 1.00
                }

                val litros = peso * 0.035 * factor
                val litrosFormatted = String.format("%.2f", litros)

                resultadoText = "$nombre debe beber aproximadamente $litrosFormatted litros de agua al día"
                errorText = null
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular consumo de agua")
        }

        Spacer(modifier = Modifier.height(16.dp))

        errorText?.let {
            Text(text = it, color = Color.Red)
        }

        resultadoText?.let {
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraAguaPreview() {
    Practica1Moviles22200168Theme {
        CalculadoraAguaScreen(onBack = {})
    }
}
