package com.sameer.colours

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ModelClassEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ccode: String,
    val tstamp: String
)