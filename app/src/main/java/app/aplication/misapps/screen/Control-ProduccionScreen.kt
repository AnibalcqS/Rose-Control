package app.aplication.misapps.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.aplication.misapps.components.BaseScreen
import app.aplication.misapps.components.StatusCard
import app.aplication.misapps.utils.Routes

@Composable
fun Control_ProduccionScreen(navController: NavController) {
    BaseScreen("Validación de Datos", Color(0xFF455A64), navController) {
        StatusCard("¡Registro aceptado correctamente!", Icons.Default.CheckCircle, Color(0xFF4CAF50)) {

        }
        Spacer(Modifier.height(16.dp))
        StatusCard("Cantidad alta, confirme el envío", Icons.Default.Warning, Color(0xFFFFB300)) { }
    }
}

