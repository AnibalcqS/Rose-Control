package app.aplication.misapps.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Bomba(
    val nombre: String,
    val ultimaFechaCambio: LocalDate
)

@Composable
fun MantenimientoScreen(navController: NavController) {

    val bombas = listOf(
        Bomba("Bomba 1", LocalDate.of(2026,1,10)),
        Bomba("Bomba 2", LocalDate.of(2026,1,25)),
        Bomba("Bomba 3", LocalDate.of(2026,2,1))
    )

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        items(bombas) { bomba ->

            val proximoCambio = bomba.ultimaFechaCambio.plusDays(30)
            val hoy = LocalDate.now()

            val estadoColor = when {
                proximoCambio.isBefore(hoy) -> Color.Red
                proximoCambio.isBefore(hoy.plusDays(5)) -> Color(0xFFFFA000)
                else -> Color(0xFF4CAF50)
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text("Equipo: ${bomba.nombre}")

                    Spacer(Modifier.height(6.dp))

                    Text("Último cambio: ${bomba.ultimaFechaCambio}")

                    Text("Próximo cambio: $proximoCambio", color = estadoColor)
                }
            }
        }
    }
}
