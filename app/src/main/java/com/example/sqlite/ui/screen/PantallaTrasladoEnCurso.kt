package com.example.sqlite.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Composable
fun PantallaTrasladoEnCurso(
    traslado: TrasladoEntity,
    onIrAFinalizar: () -> Unit,
    onVolver: () -> Unit
) {
    Column {
        Text("Viaje en curso (ID: ${traslado.id})")
        Text("Vehículo asignado: ${traslado.vehiculoId}")
        Text("Destino: ${traslado.destinoId}")
        Text("Odómetro inicial: ${traslado.kmInicio}")
        Text("Hora de inicio: ${traslado.timestampInicio}")

        Button(onClick = onIrAFinalizar) {
            Text("Llegué al destino (Finalizar)")
        }

        Button(onClick = onVolver) {
            Text("Volver al historial")
        }
    }
}