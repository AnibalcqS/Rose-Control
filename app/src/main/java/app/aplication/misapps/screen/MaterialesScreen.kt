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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.aplication.misapps.components.BaseScreen

@Composable
fun MaterialesScreen(navController: NavController) {
    val items = listOf(
        Triple("Mondial", "120 Tallos - 10:15 AM", Color(0xFF4CAF50)),
        Triple("Explorer", "300 Tallos - 11:00 AM", Color(0xFFFFB300)),
        Triple("Paloma", "80 Tallos - 11:45 AM", Color(0xFF2196F3))
    )

    BaseScreen("Historial del Turno", Color(0xFF1565C0), navController) {
        LazyColumn {
            items(items) { (varie, info, col) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(col)
                                .padding(8.dp)
                        ) {
                            Text(varie, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column(Modifier.padding(12.dp)) {
                            Text(info, fontWeight = FontWeight.Medium)
                            Text("Supervisor: Carla", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}
