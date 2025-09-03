package com.example.main.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.main.CompReusable.AlertaFlotante
import com.example.main.CompReusable.NivelAlerta
import com.example.main.Navigation.AppScreens
import com.example.main.R

@Composable
fun HomeScreen(navController: NavController) {
    var mostrarAlerta by rememberSaveable { mutableStateOf(false) }
    var hablar by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sofia Arboleda") }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    // Ajusta al nombre exacto de tu ruta si difiere
                    navController.navigate(AppScreens.AddRiskZone.name)
                },
                text = { Text("Agregar zona de riesgo") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true, onClick = { /*Inicio*/ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = false, onClick = { navController.navigate(AppScreens.RiskZones.name) },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alertas") },
                    label = { Text("Alertas") }
                )
                NavigationBarItem(
                    selected = false, onClick = { navController.navigate(AppScreens.ChatScreen.name) },
                    icon = { Icon(Icons.Default.Chat, contentDescription = "Chat") },
                    label = { Text("Chat") }
                )
                NavigationBarItem(
                    selected = false, onClick = { navController.navigate(AppScreens.ProfileScreen.name) },
                    icon = { Icon(Icons.Default.Map, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            // Encabezado pequeño (actividad y zona)
            Text(
                text = "N° Actividad 1012    Zona 3",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Anuncio del supervisor
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Anuncio del Supervisor",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "A partir de ahora todas las zonas implementarán Safety First para la Gestión de Riesgos."
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Alertas",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))

            // Tarjetas de alerta (simulan las de la imagen)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AlertaCard(
                    titulo = "Diego",
                    descripcion = "Cargas Suspendidas Zona D",
                    onClick = { mostrarAlerta = true }
                )
                AlertaCard(
                    titulo = "Alejandro",
                    descripcion = "Riesgo Eléctrico Zona B",
                    onClick = { mostrarAlerta = true }
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Mapa de la Obra",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))

            // Mapa (placeholder). Puedes reemplazar por tu mapa real (Google Maps, etc.)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { mostrarAlerta = true }, // tocar el mapa también abre alerta
                contentAlignment = Alignment.Center
            ) {
                Text("Mapa (placeholder) — toca para simular alerta")
            }

            Spacer(Modifier.height(16.dp))

            // Botón grande rojo/verde (según tu recurso actual)
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = if (!hablar)
                        painterResource(R.drawable.boton_rojo_2)
                    else
                        painterResource(R.drawable.boton_verde),
                    contentDescription = "Botón de advertencia",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(240.dp)
                        .height(240.dp)
                        .clickable {
                            hablar = !hablar
                            mostrarAlerta = true
                        }
                )
            }
        }

        // Alerta flotante tipo modal
        AlertaFlotante(
            visible = mostrarAlerta,
            titulo = "¡ALERTA! Zona con poca luz",
            descripcion = "¿Desea enviar advertencia de esta zona al supervisor?",
            nivel = NivelAlerta.ADVERTENCIA,
            onAdvertirClick = {
                mostrarAlerta = false
                navController.navigate(AppScreens.ChatScreen.name) // simula enviar al supervisor
            },
            onDismiss = { mostrarAlerta = false }
        )
    }
}

@Composable
private fun AlertaCard(
    titulo: String,
    descripcion: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .weight(1f)
            .height(110.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(titulo, style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(4.dp))
            Text(descripcion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
