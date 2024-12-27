package com.sameer.colours

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ModelClassEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {


    companion object {
       const val dbname ="database"
    }

    abstract fun getAppDao(): AppDao



}

