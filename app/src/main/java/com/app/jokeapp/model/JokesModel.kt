package com.app.jokeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class JokesModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("jokes") var joke: String,
    @ColumnInfo("time") var time: Long
)