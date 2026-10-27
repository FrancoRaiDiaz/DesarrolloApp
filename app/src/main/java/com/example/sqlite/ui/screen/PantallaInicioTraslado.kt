package com.example.sqlite.ui.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sqlite.ui.viewmodel.TrasladoViewModel

@Composable
fun PantallaInicioTraslado(
    viewModel: TrasladoViewModel,
    onVolver: () -> Unit
) {
    var vehiculoId by remember { mutableStateOf("") }
    var destinoId by remember { mutableStateOf("") }
    var kmInicio by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column {
        Text("Formulario de Inicio de Traslado")

        TextField(value = vehiculoId, onValueChange = { vehiculoId = it }, label = { Text("ID Vehículo") })
        TextField(value = destinoId, onValueChange = { destinoId = it }, label = { Text("ID Destino") })
        TextField(value = kmInicio, onValueChange = { kmInicio = it }, label = { Text("Km Inicial") })

        if (mensajeError.isNotEmpty()) {
            Text(mensajeError)
        }

        Button(onClick = {
            val vId = vehiculoId.toIntOrNull()
            val dId = destinoId.toIntOrNull()
            val km = kmInicio.toDoubleOrNull()

            if (vId != null && dId != null && km != null) {
                viewModel.iniciarTraslado(
                    vehiculoId = vId,
                    destinoId = dId,
                    kmInicio = km,
                    latitud = null,
                    longitud = null
                )
                onVolver()
            } else {
                mensajeError = "Por favor, ingresa números válidos"
            }
        }) {
            Text("Guardar y Empezar")
        }

        Button(onClick = onVolver) {
            Text("Cancelar")
        }
    }
}