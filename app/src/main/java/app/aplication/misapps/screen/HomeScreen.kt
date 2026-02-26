package app.aplication.misapps.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.aplication.misapps.utils.Routes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import app.aplication.misapps.R
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val auth = FirebaseAuth.getInstance()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showLogoutDialog by remember { mutableStateOf(false) }

    // detectar ruta actual para activar botones del bottom bar
    val currentRoute = navController.currentBackStackEntry?.destination?.route
// 🔥 DIALOGO DE CONFIRMACION
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Estás seguro que deseas cerrar sesión?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        scope.launch { drawerState.close() }
                        auth.signOut()
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                ) {
                    Text("Sí, cerrar sesión")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 60.dp),
                drawerContainerColor = Color.White.copy(alpha = 0.60f)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {

                    DrawerItem(
                        text = "Registro Producción",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Routes.REGISTRO_PRODUCCION)
                        }
                    )

                    DrawerItem(
                        text = "Agroquímicos",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("agroquimicos")
                        }
                    )

                    DrawerItem(
                        text = "Control Producción",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("control_produccion")
                        }
                    )

                    DrawerItem(
                        text = "Materiales",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("MATERIALES")
                        }
                    )

                    DrawerItem(
                        text = "Mantenimiento",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("mantenimiento")
                        }
                    )

                    DrawerItem(
                        text = "Estado Económico",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("estado_economico")
                        }
                    )

                    DrawerItem(
                        text = "Configuración",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("configuracion")
                        }
                    )

                    DrawerItem(
                        text = "Acerca de",
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("acercade")
                        }
                    )

                    Divider()

                    DrawerItem(
                        text = "Cerrar sesión",
                        icon = Icons.Default.Logout,
                        containerColor = Color(0xFFB00020),
                        textColor = Color.White,
                        onClick = {
                            showLogoutDialog = true
                        }
                    )
                }
            }
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("RoseControl Menú") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            },

            // 🔻 BOTTOM BAR COMPLETO
            bottomBar = {
                NavigationBar {

                    NavigationBarItem(
                        selected = currentRoute == Routes.HOME,
                        onClick = { navController.navigate(Routes.HOME) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") }
                    )

                    NavigationBarItem(
                        selected = currentRoute == Routes.REGISTRO_PRODUCCION,
                        onClick = { navController.navigate(Routes.REGISTRO_PRODUCCION) },
                        icon = { Icon(Icons.Default.AddCircle, contentDescription = "Registro") },
                        label = { Text("Registro") }
                    )

                    NavigationBarItem(
                        selected = currentRoute == "control_produccion",
                        onClick = { navController.navigate("control_produccion") },
                        icon = { Icon(Icons.Default.List, contentDescription = "Producción") },
                        label = { Text("Producción") }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.popBackStack() },
                        icon = { Icon(Icons.Default.ArrowBack, contentDescription = "Atrás") },
                        label = { Text("Atrás") }
                    )
                }
            }

        ) { padding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.rosa),
                    contentDescription = "Fondo menú",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                )
            }
        }
    }
}

@Composable
fun DrawerItem(
    text: String,
    onClick: () -> Unit,
    icon: ImageVector? = null,
    containerColor: Color = Color.White.copy(alpha = 0.7f),
    textColor: Color = Color.Black
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        NavigationDrawerItem(
            label = {
                Text(
                    text = text,
                    color = textColor
                )
            },
            icon = {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = text,
                        tint = textColor
                    )
                }
            },
            selected = false,
            onClick = onClick,
            modifier = Modifier.padding(6.dp),
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
                unselectedTextColor = textColor,
                unselectedIconColor = textColor
            )
        )
    }
}