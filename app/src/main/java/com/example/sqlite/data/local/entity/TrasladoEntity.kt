package com.teqmed.teqruta.datos.local.entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "traslados")
data class TrasladoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "vehiculo_id")
    val vehiculoId: Int,

    @ColumnInfo(name = "destino")
    val destino: String,

    @ColumnInfo(name = "km_inicio")
    val kmInicio: Double,

    @ColumnInfo(name = "km_fin")
    val kmFin: Double? = null,

    @ColumnInfo(name = "latitud_inicio")
    val latitudInicio: Double? = null,

    @ColumnInfo(name = "longitud_inicio")
    val longitudInicio: Double? = null,

    @ColumnInfo(name = "timestamp_inicio")
    val timestampInicio: String,

    @ColumnInfo(name = "timestamp_fin")
    val timestampFin: String? = null
)