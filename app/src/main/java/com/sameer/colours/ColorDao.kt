package com.sameer.colours

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert
    suspend fun insert(ModelItem: ModelClassEntity)

    @Query("SELECT * FROM ModelClassEntity")
     fun getAllColors(): LiveData<List<ModelClassEntity>>
}