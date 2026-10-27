package com.example.sqlite.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Composable
fun PantallaTrasladoEnCurso(
    traslado: TrasladoEntity,
    onIrAFinalizar: () -> Unit,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Viaje en curso (ID: ${traslado.id})", style = MaterialTheme.typography.titleMedium)
        Text("Vehículo asignado: ${traslado.vehiculoId}")
        Text("Destino: ${traslado.destino}")
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