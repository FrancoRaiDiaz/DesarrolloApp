package com.teqmed.teqruta.datos.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Dao
interface TrasladoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(traslado: TrasladoEntity): Long

    @Update
    suspend fun actualizar(traslado: TrasladoEntity)

    @Delete
    suspend fun eliminar(traslado: TrasladoEntity)

    @Query("DELETE FROM traslados WHERE id = :id")
    suspend fun eliminarPorId(id: Long)

    @Query("SELECT * FROM traslados ORDER BY id DESC")
    suspend fun obtenerTodos(): List<TrasladoEntity>
}