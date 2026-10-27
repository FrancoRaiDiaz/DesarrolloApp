package com.example.sqlite.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sqlite.ui.viewmodel.TrasladoViewModel

@Composable
fun PantallaInicioTraslado(
    viewModel: TrasladoViewModel,
    onVolver: () -> Unit
) {
    var vehiculoId by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var kmInicio by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Formulario de Inicio de Traslado")

        TextField(value = vehiculoId, onValueChange = { vehiculoId = it }, label = { Text("ID Vehículo") })
        TextField(value = destino, onValueChange = { destino = it }, label = { Text("Lugar de Destino (ej: Hospital Higueras)") })
        TextField(value = kmInicio, onValueChange = { kmInicio = it }, label = { Text("Km Inicial") })

        if (mensajeError.isNotEmpty()) {
            Text(mensajeError)
        }

        Button(onClick = {
            val vId = vehiculoId.toIntOrNull()
            val km = kmInicio.toDoubleOrNull()

            if (vId != null && destino.isNotBlank() && km != null) {
                viewModel.iniciarTraslado(
                    vehiculoId = vId,
                    destino = destino,
                    kmInicio = km,
                    latitud = null,
                    longitud = null
                )
                onVolver()
            } else {
                mensajeError = "Por favor, completa los campos correctamente"
            }
        }) {
            Text("Guardar y Empezar")
        }

        Button(onClick = onVolver) {
            Text("Cancelar")
        }
    }
}