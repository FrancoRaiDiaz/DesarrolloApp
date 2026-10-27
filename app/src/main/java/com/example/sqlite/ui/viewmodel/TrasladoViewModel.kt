package com.example.sqlite.ui.viewmodel

import com.example.sqlite.data.local.entity.PeajeEntity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity
import com.teqmed.teqruta.datos.repositorio.RepositorioTrasladoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrasladoViewModel(
    private val repositorio: RepositorioTrasladoImpl
) : ViewModel() {

    // Estado reactivo que la interfaz observará para actualizar la lista automáticamente
    private val _listaTraslados = MutableStateFlow<List<TrasladoEntity>>(emptyList())
    val listaTraslados: StateFlow<List<TrasladoEntity>> = _listaTraslados.asStateFlow()

    // Carga inicial o actualización de los datos desde SQLite
    fun cargarTraslados() {
        viewModelScope.launch {
            _listaTraslados.value = repositorio.obtenerTodosLosTraslados()
        }
    }

    // Registra un nuevo viaje en la base de datos local
    fun iniciarTraslado(
        vehiculoId: Int,
        destinoId: Int,
        kmInicio: Double,
        latitud: Double?,
        longitud: Double?
    ) {
        viewModelScope.launch {
            val traslado = TrasladoEntity(
                vehiculoId = vehiculoId,
                destinoId = destinoId,
                kmInicio = kmInicio,
                latitudInicio = latitud,
                longitudInicio = longitud,
                timestampInicio = System.currentTimeMillis().toString()
            )
            repositorio.guardarInicioTraslado(traslado)
            cargarTraslados() // Actualiza la lista en pantalla
        }
    }

    // Actualiza el viaje existente con el odómetro final y guarda los peajes asociados
    fun finalizarTraslado(
        traslado: TrasladoEntity,
        kmFin: Double,
        listaPeajes: List<Double>
    ) {
        viewModelScope.launch {
            val trasladoActualizado = traslado.copy(
                kmFin = kmFin,
                timestampFin = System.currentTimeMillis().toString()
            )

            // Convierte la lista de montos en una lista de entidades PeajeEntity
            val peajesEntities = listaPeajes.map { monto ->
                PeajeEntity(trasladoId = traslado.id, monto = monto)
            }

            repositorio.finalizarTraslado(trasladoActualizado, peajesEntities)
            cargarTraslados() // Actualiza la lista en pantalla
        }
    }

    // Elimina un viaje específico de la base de datos local
    fun eliminarTraslado(traslado: TrasladoEntity) {
        viewModelScope.launch {
            repositorio.eliminarTraslado(traslado)
            cargarTraslados() // Actualiza la lista en pantalla
        }
    }
}