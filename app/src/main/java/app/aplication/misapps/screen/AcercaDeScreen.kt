package app.aplication.misapps.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcercaDeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acerca de") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Política de Privacidad",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "\uD83C\uDF39 RoseControl\n" +
                        "Acerca de la aplicación\n" +
                        "\n" +
                        "RoseControl es una aplicación diseñada para la gestión y control de la producción agrícola, especialmente enfocada en el cultivo de rosas. Permite registrar, organizar y consultar información relacionada con producción, agroquímicos, materiales, mantenimiento y estado económico.\n" +
                        "\n" +
                        "La aplicación busca mejorar la eficiencia operativa mediante un sistema digital seguro y estructurado.\n" +
                        "\n" +
                        "Desarrollador: Aníbal Cobacango\n" +
                        "Ubicación: Cayambe, Ecuador\n" +
                        "Versión: 1.0\n" +
                        "Año: 2026\n" +
                        "\n" +
                        "\uD83D\uDD10 Política de Privacidad\n" +
                        "1. Información recopilada\n" +
                        "\n" +
                        "RoseControl puede recopilar la siguiente información proporcionada por el usuario:\n" +
                        "\n" +
                        "• Datos de registro (correo electrónico y autenticación).\n" +
                        "• Información ingresada manualmente dentro de la aplicación (producción, materiales, agroquímicos, registros económicos, etc.).\n" +
                        "\n" +
                        "No se recopila información sensible del dispositivo sin consentimiento.\n" +
                        "\n" +
                        "2. Uso de la información\n" +
                        "\n" +
                        "La información recopilada se utiliza exclusivamente para:\n" +
                        "\n" +
                        "• Permitir el acceso seguro a la cuenta del usuario.\n" +
                        "• Almacenar y sincronizar los registros agrícolas.\n" +
                        "• Mejorar la funcionalidad y estabilidad de la aplicación.\n" +
                        "\n" +
                        "3. Servicios de terceros\n" +
                        "\n" +
                        "La aplicación utiliza servicios de terceros para su funcionamiento:\n" +
                        "\n" +
                        "• Servicios de autenticación y base de datos en la nube (Firebase).\n" +
                        "\n" +
                        "Estos servicios pueden recopilar información técnica necesaria para garantizar seguridad y funcionamiento adecuado.\n" +
                        "\n" +
                        "RoseControl no vende, alquila ni comparte información personal con terceros para fines comerciales.\n" +
                        "\n" +
                        "4. Seguridad\n" +
                        "\n" +
                        "Se aplican medidas razonables de seguridad para proteger la información almacenada. Sin embargo, ningún sistema es completamente infalible.\n" +
                        "\n" +
                        "5. Derechos del usuario\n" +
                        "\n" +
                        "El usuario puede solicitar la eliminación de su cuenta y datos asociados mediante los medios de contacto indicados.\n" +
                        "\n" +
                        "\uD83D\uDCC4 Términos y Condiciones\n" +
                        "1. Aceptación de los términos\n" +
                        "\n" +
                        "Al descargar y utilizar RoseControl, el usuario acepta los presentes términos y condiciones.\n" +
                        "\n" +
                        "2. Uso permitido\n" +
                        "\n" +
                        "La aplicación está destinada exclusivamente a fines de gestión agrícola.\n" +
                        "El usuario se compromete a no utilizar la aplicación para actividades ilícitas o indebidas.\n" +
                        "\n" +
                        "3. Responsabilidad\n" +
                        "\n" +
                        "El usuario es responsable de:\n" +
                        "\n" +
                        "• La veracidad de la información ingresada.\n" +
                        "• La protección de sus credenciales de acceso.\n" +
                        "• El uso adecuado de la información generada por la aplicación.\n" +
                        "\n" +
                        "4. Limitación de responsabilidad\n" +
                        "\n" +
                        "El desarrollador no será responsable por:\n" +
                        "\n" +
                        "• Pérdida de datos debido a fallas del dispositivo o conectividad.\n" +
                        "• Interrupciones temporales del servicio.\n" +
                        "• Decisiones económicas o productivas tomadas con base en la información registrada.\n" +
                        "\n" +
                        "5. Modificaciones\n" +
                        "\n" +
                        "El desarrollador podrá actualizar estos términos y políticas en futuras versiones de la aplicación.\n" +
                        "\n" +
                        "6. Contacto\n" +
                        "\n" +
                        "Para soporte o consultas relacionadas con privacidad y uso de datos, el usuario podrá comunicarse a través del correo electrónico proporcionado en la aplicación.\n" +
                        "\n" +
                        "Fecha de última actualización: Enero 2026"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Versión: 1.0",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}