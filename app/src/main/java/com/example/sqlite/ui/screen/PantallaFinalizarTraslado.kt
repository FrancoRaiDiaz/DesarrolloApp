package com.example.sqlite.ui.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sqlite.ui.viewmodel.TrasladoViewModel
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Composable
fun PantallaFinalizarTraslado(
    viewModel: TrasladoViewModel,
    traslado: TrasladoEntity,
    onVolver: () -> Unit
) {
    var kmFin by remember { mutableStateOf("") }
    var peajeInput by remember { mutableStateOf("") }
    var listaPeajes by remember { mutableStateOf(listOf<Double>()) }
    var mensajeError by remember { mutableStateOf("") }

    Column {
        Text("Cerrar Traslado (Km Inicial: ${traslado.kmInicio})")

        TextField(value = kmFin, onValueChange = { kmFin = it }, label = { Text("Kilometraje Final") })

        Text("Agregar Peajes (Opcional):")
        TextField(value = peajeInput, onValueChange = { peajeInput = it }, label = { Text("Monto del peaje") })

        Button(onClick = {
            val monto = peajeInput.toDoubleOrNull()
            if (monto != null && monto > 0) {
                listaPeajes = listaPeajes + monto
                peajeInput = "" // Limpia el campo tras agregar
            }
        }) {
            Text("Añadir Peaje")
        }

        // Muestra la lista de peajes agregados en tiempo real
        LazyColumn {
            items(listaPeajes) { monto ->
                Text("Peaje agregado: $$monto")
            }
        }

        if (mensajeError.isNotEmpty()) {
            Text(mensajeError)
        }

        Button(onClick = {
            val kF = kmFin.toDoubleOrNull()
            if (kF != null) {
                if (kF >= traslado.kmInicio) {
                    viewModel.finalizarTraslado(traslado, kF, listaPeajes)
                    onVolver()
                } else {
                    mensajeError = "El Km Final no puede ser menor al Km Inicial"
                }
            } else {
                mensajeError = "Ingresa un Km Final válido"
            }
        }) {
            Text("Guardar y Cerrar Viaje")
        }

        Button(onClick = onVolver) {
            Text("Cancelar")
        }
    }
}