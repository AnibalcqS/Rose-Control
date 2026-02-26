package app.aplication.misapps.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.aplication.misapps.components.BaseScreen
import app.aplication.misapps.data.Producto

@Composable
fun CarritoScreen(
    navController: NavController,
    carrito: MutableList<Producto>
) {

    BaseScreen("Carrito de Compras", Color(0xFF37474F), navController) {

        if (carrito.isEmpty()) {

            Text(
                text = "El carrito está vacío",
                modifier = Modifier.padding(16.dp)
            )

        } else {

            LazyColumn {

                items(carrito) { producto ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(text = producto.nombre)

                        Text(
                            text = "Eliminar",
                            color = Color.Red,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .clickable {
                                    carrito.remove(producto)
                                }
                        )
                    }
                }
            }
        }
    }
}