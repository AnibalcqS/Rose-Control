package app.aplication.misapps.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.aplication.misapps.components.BaseScreen
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import app.aplication.misapps.utils.Routes
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log


@Composable
fun Registro_ProduccionScreen(navController: NavController) {

    val variedades = listOf(
        "Candelight", "Magic Times", "Lola",
        "Paloma", "Mondial", "Frutteto", "Explorer"
    )

    var variedadSeleccionada by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    BaseScreen("Registro de Tallos", Color(0xFFD32F2F), navController) {

        Column(modifier = Modifier.fillMaxSize()) {

            // GRID DE VARIEDADES
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(260.dp)
            ) {
                items(variedades) { variedad ->

                    val seleccionado = variedadSeleccionada == variedad

                    Button(
                        onClick = { variedadSeleccionada = variedad },
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (seleccionado)
                                Color(0xFFD32F2F) // rojo cuando está seleccionado
                            else
                                Color(0xFFECEFF1)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            variedad,
                            color = if (seleccionado) Color.White else Color.DarkGray,
                            fontSize = 13.sp
                        )
                    }
                }

            }

            Spacer(Modifier.height(20.dp))

            // INPUT CANTIDAD
            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad de tallos") },
                placeholder = { Text("Ej: 120") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(3.dp))

// BOTONES JUSTO DEBAJO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        if (variedadSeleccionada.isNotEmpty() && cantidad.isNotEmpty()) {
                            guardarRegistro(variedadSeleccionada, cantidad)
                            variedadSeleccionada = ""
                            cantidad = ""
                        }
                    }
                ) {
                    Text("Enviar registro")
                }

                Button(
                    onClick = {
                        navController.navigate(Routes.HISTORIAL)
                    }
                ) {
                    Text("Historial")
                }
            }

        }
    }
}


fun guardarRegistro(variedad: String, cantidad: String) {

    val db = FirebaseFirestore.getInstance()

    val registro = hashMapOf(
        "variedad" to variedad,
        "cantidad" to cantidad.toInt(),
        "fecha" to System.currentTimeMillis()
    )

    db.collection("produccion")
        .add(registro)
        .addOnSuccessListener {
            Log.d("FIRESTORE", "Registro guardado correctamente")
        }
        .addOnFailureListener { e ->
            Log.e("FIRESTORE", "Error al guardar", e)
        }
}
