package com.practica1Moviles.Ticona.data.local

// TODO: Añadir las anotaciones de Room (@Dao, @Insert, @Query, @Delete) cuando se configure kapt/room-compiler
// import androidx.room.*

interface AutoDAO {
    suspend fun insert(auto: AutoEntity)

    suspend fun getAll(): List<AutoEntity>

    suspend fun delete(auto: AutoEntity)
}
