package com.sameer.colours

import android.app.Application
import androidx.room.Room

class MyApplication : Application(){

    companion object {
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase= Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.dbname
        ).build()
    }
}