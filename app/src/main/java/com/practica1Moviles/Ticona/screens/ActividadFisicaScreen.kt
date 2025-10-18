package com.practica1Moviles.Ticona.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica1Moviles.Ticona.ui.theme.Practica1Moviles22200168Theme
import kotlinx.coroutines.launch
import java.util.Locale

// Data class para cada actividad registrada
data class ActivityRecord(
    val actividad: String,
    val duracion: Int,
    val intensidad: String,
    val caloriasPorMinuto: Double,
    val totalCalorias: Double
)

@Composable
fun ActividadFisicaScreen(onBack: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val actividades = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    val intensidadNiveles = listOf("Baja", "Media", "Alta")

    val actividadCalorias = mapOf(
        "Correr" to 10,
        "Caminar" to 5,
        "Nadar" to 8,
        "Ciclismo" to 7,
        "Yoga" to 4
    )

    val intensidadFactores = mapOf(
        "Baja" to 0.8,
        "Media" to 1.0,
        "Alta" to 1.2
    )

    var selectedActividad by remember { mutableStateOf("Correr") }
    var duracionText by remember { mutableStateOf("") }
    var selectedIntensidad by remember { mutableStateOf("Media") }
    var resultadoText by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }

    var calculadoCaloriasPorMinuto by remember { mutableStateOf<Double?>(null) }
    var calculadoTotalCalorias by remember { mutableStateOf<Double?>(null) }

    val registros = remember { mutableStateListOf<ActivityRecord>() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Registro de Actividad Física",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Dropdown para actividad
            Text(text = "Actividad:")
            Box {
                TextButton(onClick = { expanded = true }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = selectedActividad)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Abrir selector de actividad"
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    actividades.forEach { actividad ->
                        DropdownMenuItem(
                            text = { Text(actividad) },
                            onClick = {
                                selectedActividad = actividad
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Duración
            OutlinedTextField(
                value = duracionText,
                onValueChange = { duracionText = it },
                label = { Text("Duración (minutos)") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Intensidad
            Text(text = "Intensidad:")
            intensidadNiveles.forEach { nivel ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedIntensidad == nivel,
                        onClick = { selectedIntensidad = nivel }
                    )
                    Text(text = nivel)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones: Calcular y Registrar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        val duracion = duracionText.toIntOrNull()
                        if (duracion == null || duracion <= 0) {
                            scope.launch {
                                snackbarHostState.showSnackbar("La duración debe ser un número entero positivo.")
                            }
                            calculadoCaloriasPorMinuto = null
                            calculadoTotalCalorias = null
                            resultadoText = null
                            return@Button
                        }

                        val caloriasBase = actividadCalorias[selectedActividad] ?: 0
                        val factorIntensidad = intensidadFactores[selectedIntensidad] ?: 1.0
                        val caloriasPorMinuto = caloriasBase * factorIntensidad
                        val totalCalorias = duracion * caloriasPorMinuto

                        calculadoCaloriasPorMinuto = caloriasPorMinuto
                        calculadoTotalCalorias = totalCalorias
                        resultadoText =
                            "Resultado: ${String.format(Locale.getDefault(), "%.2f", totalCalorias)} calorías " +
                                    "( ${String.format(Locale.getDefault(), "%.2f", caloriasPorMinuto)} cal/min )"
                        focusManager.clearFocus()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Calcular")
                }

                Button(
                    onClick = {
                        val duracion = duracionText.toIntOrNull() ?: return@Button
                        val cpm = calculadoCaloriasPorMinuto ?: return@Button
                        val total = calculadoTotalCalorias ?: return@Button

                        registros.add(
                            ActivityRecord(
                                actividad = selectedActividad,
                                duracion = duracion,
                                intensidad = selectedIntensidad,
                                caloriasPorMinuto = cpm,
                                totalCalorias = total
                            )
                        )

                        scope.launch {
                            snackbarHostState.showSnackbar("Actividad registrada")
                        }

                        calculadoCaloriasPorMinuto = null
                        calculadoTotalCalorias = null
                        resultadoText = null
                        duracionText = ""
                    },
                    enabled = calculadoCaloriasPorMinuto != null,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Registrar")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            resultadoText?.let {
                Text(it, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de registros
            Text(text = "Registro de actividades:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (registros.isEmpty()) {
                Text(text = "No hay actividades registradas.")
            } else {
                // Cabecera
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("Actividad", modifier = Modifier.weight(2f), style = MaterialTheme.typography.bodyMedium)
                    Text("Dur (min)", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                    Text("Intens.", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                    Text("Cal/min", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                    Text("Total", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                }

                Divider()

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(registros) { reg ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(reg.actividad, modifier = Modifier.weight(2f))
                            Text("${reg.duracion}", modifier = Modifier.weight(1f))
                            Text(reg.intensidad, modifier = Modifier.weight(1f))
                            Text(String.format(Locale.getDefault(), "%.2f", reg.caloriasPorMinuto), modifier = Modifier.weight(1f))
                            Text(String.format(Locale.getDefault(), "%.2f", reg.totalCalorias), modifier = Modifier.weight(1f))
                        }
                        Divider()
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onBack, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Volver")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActividadFisicaPreview() {
    Practica1Moviles22200168Theme {
        ActividadFisicaScreen(onBack = {})
    }
}
