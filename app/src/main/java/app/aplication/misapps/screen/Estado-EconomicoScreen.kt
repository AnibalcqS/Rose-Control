package app.aplication.misapps.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EstadoEconomicoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Estado Económico de la Empresa 📊")
        Spacer(modifier = Modifier.height(10.dp))
        Text("Aquí puedes mostrar ingresos, egresos, balance, etc.")
    }
}
