package com.example.sqlite.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sqlite.ui.theme.*
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
            .background(Slate50)
    ) {
        // App Top Bar
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
                        text = "TeqRuta",
                        color = Color.White,
                        fontSize = 20.sp,
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
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // Sync Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(11.dp))
                        .background(Green100)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "● En Línea",
                        color = SyncGreen,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                // User Greeting Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkBlue)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "TÉCNICO DE SERVICIO",
                            color = Color(0xFF93C5FD),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Carlos Morales",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Sede Concepción • Base Central",
                            color = Color(0xFFDBEAFE),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            item {
                // CTA: Start Trip Big Button
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                        .clickable { onNuevoTraslado() },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Sky600)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Sky400),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "🚗", fontSize = 22.sp)
                            }
                            Column {
                                Text(
                                    text = "Iniciar Nuevo Traslado",
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Registrar salida y odómetro inicial",
                                    color = Color(0xFFE0F2FE),
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Text(
                            text = "➔",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                // Vehicle Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Slate200)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(text = "🚙", fontSize = 22.sp)
                            Column {
                                Text(
                                    text = "Vehículo Asignado: Peugeot Partner",
                                    color = Slate900,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Patente: HH-XX-88 • Rendimiento: 14.5 km/L",
                                    color = Slate600,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(11.dp))
                                .background(Green100)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Activo",
                                color = SyncGreen,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {
                // Recent Trips Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Traslados Recientes Hoy",
                        color = Slate900,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Ver Todos",
                        color = Blue600,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(listaTraslados) { traslado ->
                val isFinalizado = traslado.kmFin != null
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Slate200)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Destino: ${traslado.destino}",
                                color = Slate900,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            val badgeBg = if (isFinalizado) Green100 else Amber100
                            val badgeText = if (isFinalizado) "✓ Finalizado" else "⏳ En Curso"
                            val badgeTextColor = if (isFinalizado) SyncGreen else Amber800

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(11.dp))
                                    .background(badgeBg)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = badgeText,
                                    color = badgeTextColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Text(
                            text = "Vehículo ID: ${traslado.vehiculoId} • Km Inicio: ${traslado.kmInicio}" +
                                    if (isFinalizado) " • Km Fin: ${traslado.kmFin}" else "",
                            color = Slate600,
                            fontSize = 12.sp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (!isFinalizado) {
                                TextButton(onClick = { onVerEnCurso(traslado) }) {
                                    Text("Ver Estado", color = Sky600, fontSize = 12.sp)
                                }
                                TextButton(onClick = { onFinalizarTraslado(traslado) }) {
                                    Text("Finalizar", color = Red600, fontSize = 12.sp)
                                }
                            }
                            TextButton(onClick = { viewModel.eliminarTraslado(traslado) }) {
                                Text("Eliminar", color = Slate600, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
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
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(text = "🏠", fontSize = 18.sp)
                    Text(text = "Inicio", color = DarkBlue, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(DarkBlue))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.clickable { onNuevoTraslado() }
                ) {
                    Text(text = "🚗", fontSize = 18.sp)
                    Text(text = "Traslados", color = Slate600, fontSize = 11.sp, fontWeight = FontWeight.Medium)
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
