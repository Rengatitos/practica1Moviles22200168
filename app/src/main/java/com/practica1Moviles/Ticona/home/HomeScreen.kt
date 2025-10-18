package com.practica1Moviles.Ticona.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica1Moviles.Ticona.navigation.ROUTE_ACTIVIDAD
import com.practica1Moviles.Ticona.navigation.ROUTE_CALCULADORA
import com.practica1Moviles.Ticona.navigation.ROUTE_CATALOG
import com.practica1Moviles.Ticona.ui.theme.Practica1Moviles22200168Theme

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Práctica 1 - Moviles 22200168", style = MaterialTheme.typography.headlineSmall,)

        Button(
            onClick = { onNavigate(ROUTE_CALCULADORA) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Calculadora de consumo de Agua")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigate(ROUTE_CATALOG) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Catalogo de Autos deportivos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigate(ROUTE_ACTIVIDAD) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Registro de Actividad Física")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Practica1Moviles22200168Theme {
        HomeScreen(onNavigate = {})
    }
}