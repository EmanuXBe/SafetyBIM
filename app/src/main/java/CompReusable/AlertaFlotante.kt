package com.example.main.CompReusable

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

enum class NivelAlerta { INFORMACION, ADVERTENCIA, PELIGRO }

@Composable
fun AlertaFlotante(
    visible: Boolean,
    titulo: String,
    descripcion: String,
    nivel: NivelAlerta = NivelAlerta.ADVERTENCIA,
    onAdvertirClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!visible) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val color = when (nivel) {
                    NivelAlerta.INFORMACION -> MaterialTheme.colorScheme.primary
                    NivelAlerta.ADVERTENCIA -> MaterialTheme.colorScheme.tertiary
                    NivelAlerta.PELIGRO -> MaterialTheme.colorScheme.error
                }
                Icon(
                    imageVector = when (nivel) {
                        NivelAlerta.PELIGRO -> Icons.Filled.Report
                        else -> Icons.Filled.Warning
                    },
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(titulo, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(descripcion, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) { Text("Cerrar") }
                    Button(
                        onClick = onAdvertirClick,
                        modifier = Modifier.weight(1f)
                    ) { Text("Advertir") }
                }
            }
        }

    }
}
