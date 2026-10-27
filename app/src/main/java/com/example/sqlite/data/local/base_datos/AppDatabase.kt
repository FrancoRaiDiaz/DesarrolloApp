package com.example.sqlite.data.local.base_datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqlite.data.local.dao.PeajeDao
import com.example.sqlite.data.local.dao.UsuarioDao
import com.example.sqlite.data.local.entity.PeajeEntity
import com.example.sqlite.data.local.entity.UsuarioEntity
import com.teqmed.teqruta.datos.local.dao.TrasladoDao
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity

@Database(
    entities = [TrasladoEntity::class, UsuarioEntity::class, PeajeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BaseDatosApp : RoomDatabase() {

    abstract fun trasladoDao(): TrasladoDao
    abstract fun userDao(): UsuarioDao
    abstract fun peajeDaoo(): PeajeDao

    companion object {
        @Volatile
        private var INSTANCIA: BaseDatosApp? = null

        fun obtenerInstancia(context: Context): BaseDatosApp {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatosApp::class.java,
                    "teqruta_db"
                ).build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}
