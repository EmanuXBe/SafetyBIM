package com.example.main.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddRiskZone(navController: NavController) {
    // Estado del formulario
    var nombreZona by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }

    // Lista de riesgos y seleccionados (estado persistente)
    val tiposRiesgo = listOf(
        "Riesgo eléctrico",
        "Caída de objetos",
        "Trabajo en altura",
        "Espacios confinados",
        "Incendio/Explosión",
        "Atmósfera peligrosa",
        "Tránsito de maquinaria",
        "Resbalón/Tropiezo",
        "Proyección de partículas",
        "Ruido/Vibración",
        "Iluminación deficiente"
    )
    val seleccionados = rememberSaveable { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar zona de riesgo") }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nombreZona,
                onValueChange = { nombreZona = it },
                label = { Text("Nombre de la zona") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción / Observaciones") },
                minLines = 3,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Tipos de riesgo",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(tiposRiesgo) { tipo ->
                    RiesgoItem(
                        titulo = tipo,
                        checked = seleccionados.contains(tipo),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                if (!seleccionados.contains(tipo)) seleccionados.add(tipo)
                            } else {
                                seleccionados.remove(tipo)
                            }
                        }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) { Text("Cancelar") }

                Button(
                    onClick = {
                        // Aquí podrías guardar en BD/Cloud o enviar al backend.
                        // Por ahora, volvemos atrás.
                        navController.popBackStack()
                    },
                    enabled = nombreZona.isNotBlank() && seleccionados.isNotEmpty(),
                    modifier = Modifier.weight(1f)
                ) { Text("Guardar") }
            }
        }
    }
}

@Composable
private fun RiesgoItem(
    titulo: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        tonalElevation = if (checked) 2.dp else 0.dp,
        shape = MaterialTheme.shapes.medium
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(titulo, style = MaterialTheme.typography.bodyLarge)
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        }
    }
}
