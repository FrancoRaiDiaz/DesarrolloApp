package com.example.sqlite.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    Column {
        Button(onClick = onNuevoTraslado) {
            Text("Iniciar Nuevo Traslado")
        }

        LazyColumn {
            items(listaTraslados) { traslado ->
                Row {
                    Column {
                        Text("ID: ${traslado.id} | Vehículo: ${traslado.vehiculoId}")
                        Text("Km Inicio: ${traslado.kmInicio}")
                        if (traslado.kmFin != null) {
                            Text("Estado: Finalizado (Km Fin: ${traslado.kmFin})")
                        } else {
                            Text("Estado: En Curso")
                        }
                    }

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