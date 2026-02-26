package app.aplication.misapps.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EditarRegistroScreen(navController: NavController, id: String?) {

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var variedad by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    // 🔹 Cargar datos actuales del registro
    LaunchedEffect(id) {
        if (id != null) {
            db.collection("produccion")
                .document(id)
                .get()
                .addOnSuccessListener { doc ->
                    variedad = doc.getString("variedad") ?: ""
                    cantidad = doc.getLong("cantidad")?.toString() ?: ""
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Editar Registro")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = variedad,
            onValueChange = { variedad = it },
            label = { Text("Variedad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (id != null) {
                    db.collection("produccion")
                        .document(id)
                        .update(
                            mapOf(
                                "variedad" to variedad,
                                "cantidad" to cantidad.toInt()
                            )
                        )
                        .addOnSuccessListener {
                            Toast.makeText(context, "Registro actualizado", Toast.LENGTH_SHORT).show()
                            navController.popBackStack() // volver al historial
                        }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }
    }
}
