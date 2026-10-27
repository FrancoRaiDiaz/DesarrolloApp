package com.example.sqlite.data.local.entity

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Entity(
    tableName = "peajes",
    foreignKeys = [
        ForeignKey(
            entity = TrasladoEntity::class,
            parentColumns = ["id"],
            childColumns = ["traslado_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PeajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "traslado_id")
    val trasladoId: Long,

    val monto: Double,

    val detalle: String? = null
)