package com.teqmed.teqruta.datos.repositorio

import com.example.sqlite.data.local.dao.PeajeDao
import com.example.sqlite.data.local.entity.PeajeEntity
import com.teqmed.teqruta.datos.local.dao.TrasladoDao
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity


class RepositorioTrasladoImpl(
    private val trasladoDao: TrasladoDao,
    private val peajeDao: PeajeDao
) {
    suspend fun guardarInicioTraslado(traslado: TrasladoEntity): Long {
        return trasladoDao.insertar(traslado)
    }

    suspend fun finalizarTraslado(traslado: TrasladoEntity, peajes: List<PeajeEntity>) {
        trasladoDao.actualizar(traslado)
        if (peajes.isNotEmpty()) {
            peajeDao.insertarLista(peajes)
        }
    }

    suspend fun eliminarTraslado(traslado: TrasladoEntity) {
        trasladoDao.eliminar(traslado)
    }

    suspend fun obtenerTodosLosTraslados(): List<TrasladoEntity> {
        return trasladoDao.obtenerTodos()
    }
}