package app.aplication.misapps.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.aplication.misapps.components.BaseScreen
import app.aplication.misapps.data.Producto
import app.aplication.misapps.data.listaAgroquimicos
import app.aplication.misapps.utils.Routes

@Composable
fun AgroquimicosScreen(
    navController: NavController,
    carrito: MutableList<Producto>
) {

    BaseScreen("Agroquímicos", Color(0xFF455A64), navController) {

        Button(
            onClick = { navController.navigate(Routes.CARRITO) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🛒 Ver carrito (${carrito.size})")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(listaAgroquimicos) { producto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = producto.nombre,
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = {
                                if (!carrito.contains(producto)) {
                                    carrito.add(producto)
                                }
                            }
                        ) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}