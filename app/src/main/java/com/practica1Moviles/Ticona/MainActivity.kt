package com.practica1Moviles.Ticona

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.practica1Moviles.Ticona.navigation.AppNavGraph
import com.practica1Moviles.Ticona.ui.theme.Practica1Moviles22200168Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Forzar tema claro y desactivar dynamicColor para asegurar que se use la paleta personalizada
            Practica1Moviles22200168Theme(darkTheme = false, dynamicColor = false) {
                // Lanza el grafo de navegación principal que contiene la pantalla Home como startDestination
                AppNavGraph()
            }
        }
    }
}