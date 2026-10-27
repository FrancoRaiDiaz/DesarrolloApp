package com.example.sqlite.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val email: String,
    val rol: String,

    @ColumnInfo(name = "token_jwt")
    val tokenJwt: String // Token para autenticar las peticiones a la API Flask[cite: 3]
)
