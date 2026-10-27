package com.example.sqlite.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sqlite.ui.viewmodel.TrasladoViewModel
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Composable
fun PantallaPrincipal(
    viewModel: TrasladoViewModel,
    onNuevoTraslado: () -> Unit,
    onVerEnCurso: (TrasladoEntity) -> Unit,
    onFinalizarTraslado: (TrasladoEntity) -> Unit
) {
    val listaTraslados by viewModel.listaTraslados.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onNuevoTraslado,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Iniciar Nuevo Traslado")
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listaTraslados) { traslado ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Destino: ${traslado.destino}")
                            Text("Vehículo: ${traslado.vehiculoId} | Km: ${traslado.kmInicio}")
                            if (traslado.kmFin != null) {
                                Text("Estado: Finalizado (Fin: ${traslado.kmFin})")
                            } else {
                                Text("Estado: En Curso")
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            if (traslado.kmFin == null) {
                                Button(onClick = { onVerEnCurso(traslado) }) { Text("Ver") }
                                Button(onClick = { onFinalizarTraslado(traslado) }) { Text("Finalizar") }
                            }
                            Button(onClick = { viewModel.eliminarTraslado(traslado) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}