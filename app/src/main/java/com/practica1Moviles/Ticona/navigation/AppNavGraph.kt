package com.practica1Moviles.Ticona.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practica1Moviles.Ticona.home.HomeScreen
import com.practica1Moviles.Ticona.screens.CalculadoraAguaScreen
import com.practica1Moviles.Ticona.screens.CatalogoAutoScreen
import com.practica1Moviles.Ticona.screens.ActividadFisicaScreen

// Rutas significativas
const val ROUTE_HOME = "home"
const val ROUTE_CALCULADORA = "Calculadora-agua"
const val ROUTE_ACTIVIDAD = "calculadora_actividad-fisica"
const val ROUTE_CATALOG = "catalogo-autos"

// Grafo de navegación principal de la app.
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTE_HOME) {
        composable(ROUTE_HOME) {
            HomeScreen { route ->
                // Navega a la ruta especificada por el HomeScreen evitando apilar múltiples instancias
                navController.navigate(route) {
                    launchSingleTop = true
                }
            }
        }

        composable(ROUTE_CALCULADORA) {
            CalculadoraAguaScreen(onBack = { navController.popBackStack(ROUTE_HOME, false) })
        }

        composable(ROUTE_CATALOG) {
            CatalogoAutoScreen(onBack = { navController.popBackStack(ROUTE_HOME, false) })
        }

        composable(ROUTE_ACTIVIDAD) {
            ActividadFisicaScreen(onBack = { navController.popBackStack(ROUTE_HOME, false) })
        }
    }
}