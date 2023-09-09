package com.app.jokeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.jokeapp.model.JokesModel

@Database(entities = [JokesModel::class], version = 1, exportSchema = false)
abstract class JokesAppDatabase : RoomDatabase() {
    abstract fun jokesAppDao(): JokeAppDao


}