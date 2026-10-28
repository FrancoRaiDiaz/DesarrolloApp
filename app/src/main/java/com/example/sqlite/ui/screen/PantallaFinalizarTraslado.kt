package com.example.sqlite.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sqlite.ui.theme.*
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
    var observaciones by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Slate50)
    ) {
        // Top Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = DarkBlue
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Cierre de Traslado",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Blue600)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Técnico",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // Body Content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Step Header Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEFF6FF))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cierre de Viaje (Km Inicial: ${traslado.kmInicio})",
                        color = DarkBlue,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Kilometraje Final
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "KILOMETRAJE FINAL",
                    color = Color(0xFF334155),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = kmFin,
                    onValueChange = { kmFin = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Ingrese Km Final") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue600,
                        unfocusedBorderColor = Slate300,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            // Peajes Declarados
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "PEAJES (OPCIONAL)",
                    color = Color(0xFF334155),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = peajeInput,
                        onValueChange = { peajeInput = it },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        label = { Text("Monto del peaje") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                    Button(
                        onClick = {
                            val monto = peajeInput.toDoubleOrNull()
                            if (monto != null && monto > 0) {
                                listaPeajes = listaPeajes + monto
                                peajeInput = ""
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Blue600)
                    ) {
                        Text("Añadir")
                    }
                }

                listaPeajes.forEachIndexed { index, monto ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Slate300)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Peaje agregado: $${monto.toInt()}", color = Slate900, fontSize = 13.sp)
                            TextButton(onClick = {
                                listaPeajes = listaPeajes.filterIndexed { i, _ -> i != index }
                            }) {
                                Text("✕", color = Red600, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            // Observations input
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "OBSERVACIONES",
                    color = Color(0xFF334155),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = observaciones,
                    onValueChange = { observaciones = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text("Notas o motivo del desplazamiento") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            if (mensajeError.isNotEmpty()) {
                Text(text = mensajeError, color = Red600, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Submit Button
            Button(
                onClick = {
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
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SyncGreen)
            ) {
                Text(
                    text = "Guardar y Cerrar Viaje",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Volver Button
            Button(
                onClick = onVolver,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Slate100)
            ) {
                Text(
                    text = "Cancelar",
                    color = Color(0xFF475569),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Bottom Navigation Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            border = BorderStroke(1.dp, Slate200)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.clickable { onVolver() }
                ) {
                    Text(text = "🏠", fontSize = 18.sp)
                    Text(text = "Inicio", color = Slate600, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(text = "🚗", fontSize = 18.sp)
                    Text(text = "Traslados", color = DarkBlue, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(DarkBlue))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(text = "🔧", fontSize = 18.sp)
                    Text(text = "Vehículo", color = Slate600, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(text = "👤", fontSize = 18.sp)
                    Text(text = "Perfil", color = Slate600, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
