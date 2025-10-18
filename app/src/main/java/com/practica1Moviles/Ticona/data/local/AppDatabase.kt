package com.practica1Moviles.Ticona.data.local

import androidx.room.RoomDatabase

// TODO: Anotar con @Database cuando se configure kapt/room-compiler en el build.gradle
// @Database(entities = [AutoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // TODO: Proveer implementación del DAO cuando se configure Room
    abstract fun autoDao(): AutoDAO
}
