package com.example.main.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditProfileScreen(onSaveClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Actualizar cuenta") })
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Pantalla temporal para actualizar datos de usuario")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onSaveClick) {
                Text("Guardar")
            }
        }
    }
}
