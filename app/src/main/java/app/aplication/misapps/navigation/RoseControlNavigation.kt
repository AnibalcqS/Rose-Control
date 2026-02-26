package app.aplication.misapps.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.aplication.misapps.data.Producto
import app.aplication.misapps.screen.AcercaDeScreen
import app.aplication.misapps.screens.*
import app.aplication.misapps.utils.Routes
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf

@Composable
fun RoseControlNavigation() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) Routes.HOME else Routes.LOGIN
// 👇 CARRITO GLOBAL
    val carrito = remember { mutableStateListOf<Producto>() }


    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.REGISTER) { RegisterScreen(navController) }
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.REGISTRO_PRODUCCION) { Registro_ProduccionScreen(navController) }
        composable(Routes.AGROQUIMICOS) { AgroquimicosScreen(navController, carrito) }
        composable(Routes.CONTROL_PRODUCCION) { Control_ProduccionScreen(navController) }
        composable(Routes.MATERIALES) { MaterialesScreen(navController) }
        composable(Routes.MANTENIMIENTO) { MantenimientoScreen(navController) }
        composable(Routes.ESTADO_ECONOMICO) { EstadoEconomicoScreen(navController) }
        composable(Routes.CONFIGURACION) { ConfiguracionScreen(navController) }
        composable(Routes.HISTORIAL) { HistorialScreen(navController) }
        composable(Routes.ACERCADE) { AcercaDeScreen(navController) }
        composable(Routes.CARRITO) { CarritoScreen(navController, carrito) }
        composable("${Routes.EDITAR_REGISTRO}/{id}") { backStackEntry ->


            val id = backStackEntry.arguments?.getString("id")

            EditarRegistroScreen(
                navController = navController,
                id = id
            )
        }
    }
}
