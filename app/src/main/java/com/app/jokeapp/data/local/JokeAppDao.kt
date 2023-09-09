package com.app.jokeapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.jokeapp.model.JokesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(entity: JokesModel)

    @Delete
    suspend fun deleteJokes(jokes: List<JokesModel>)

    @Query("DELETE FROM jokes")
    suspend fun deleteAll()

    @Query("SELECT * FROM jokes ORDER BY time DESC LIMIT 10")
    fun getLast10Jokes(): List<JokesModel>

    @Query("SELECT COUNT(*) FROM jokes")
    fun getCount(): Flow<Int>

    @Query("SELECT * FROM jokes")
    fun getAll(): List<JokesModel>


    @Query("SELECT * FROM jokes ORDER BY time DESC LIMIT 10")
    fun getLastJokes(): Flow<List<JokesModel>>
}