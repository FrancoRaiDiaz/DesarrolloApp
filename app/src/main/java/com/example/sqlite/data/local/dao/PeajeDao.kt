package com.example.sqlite.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sqlite.data.local.entity.PeajeEntity

@Dao
interface PeajeDao {
    @Insert
    suspend fun insertar(peaje: PeajeEntity): Long

    @Insert
    suspend fun insertarLista(peajes: List<PeajeEntity>)

    @Query("SELECT * FROM peajes WHERE traslado_id = :trasladoId")
    suspend fun obtenerPorTraslado(trasladoId: Long): List<PeajeEntity>
}