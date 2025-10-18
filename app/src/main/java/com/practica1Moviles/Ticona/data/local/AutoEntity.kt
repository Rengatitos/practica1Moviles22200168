package com.practica1Moviles.Ticona.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autos")
data class AutoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imagenUrl: String
)

