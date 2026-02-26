package app.aplication.misapps.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.aplication.misapps.components.BaseScreen
import app.aplication.misapps.utils.Routes
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

/* MODELO DE DATOS */
data class RegistroProduccion(
    val id: String = "",
    val variedad: String = "",
    val cantidad: Long = 0,
    val fecha: Long = 0
)

@Composable
fun HistorialScreen(navController: NavController) {

    val db = FirebaseFirestore.getInstance()
    var registros by remember { mutableStateOf(listOf<RegistroProduccion>()) }

    // control diálogo eliminar
    var showDialog by remember { mutableStateOf(false) }
    var registroAEliminar by remember { mutableStateOf<RegistroProduccion?>(null) }

    // Cargar datos desde Firestore
    LaunchedEffect(true) {
        db.collection("produccion")
            .orderBy("fecha")
            .get()
            .addOnSuccessListener { resultado ->

                val lista = mutableListOf<RegistroProduccion>()

                for (doc in resultado) {
                    val registro = RegistroProduccion(
                        id = doc.id,
                        variedad = doc.getString("variedad") ?: "",
                        cantidad = doc.getLong("cantidad") ?: 0,
                        fecha = doc.getLong("fecha") ?: 0
                    )
                    lista.add(registro)
                }

                registros = lista
            }
    }

    BaseScreen("Historial del Turno", Color(0xFF1565C0), navController) {

        LazyColumn {

            items(registros) { registro ->

                val variedad = registro.variedad
                val cantidad = registro.cantidad
                val fechaMillis = registro.fecha

                val hora = if (fechaMillis != 0L) {
                    val formato = SimpleDateFormat("hh:mm a", Locale.getDefault())
                    formato.format(Date(fechaMillis))
                } else {
                    ""
                }
                val fecha = if (fechaMillis != 0L) {
                    val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    formatoFecha.format(Date(fechaMillis))
                } else {
                    ""
                }

                val col = when (variedad) {
                    "Mondial" -> Color(0xFF4CAF50)
                    "Explorer" -> Color(0xFFFFB300)
                    "Paloma" -> Color(0xFF2196F3)
                    else -> Color(0xFFD32F2F)
                }

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
                            Text(
                                variedad,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(Modifier.padding(12.dp)) {

                            Text(
                                "$cantidad Tallos",
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                "Fecha: $fecha  Hora: $hora",
                                fontSize = 13.sp,
                                color = Color.DarkGray
                            )


                            Text(
                                "Registro desde app",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )

                            Spacer(Modifier.height(8.dp))

                            // BOTONES EDITAR Y ELIMINAR EN FILA
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        navController.navigate("${Routes.EDITAR_REGISTRO}/${registro.id}")
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Editar")
                                }

                                Button(
                                    onClick = {
                                        registroAEliminar = registro
                                        showDialog = true
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // DIÁLOGO DE CONFIRMACIÓN
    if (showDialog && registroAEliminar != null) {

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Eliminar registro") },
            text = { Text("¿Estás seguro que deseas eliminar este registro?") },

            confirmButton = {
                TextButton(
                    onClick = {

                        registroAEliminar?.let { reg ->

                            db.collection("produccion")
                                .document(reg.id)
                                .delete()
                                .addOnSuccessListener {

                                    registros = registros.filter { it.id != reg.id }
                                }
                        }

                        showDialog = false
                    }
                ) {
                    Text("Sí, eliminar", color = Color.Red)
                }
            },

            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
