package com.app.jokeapp.data.local

import com.app.jokeapp.model.JokesModel
import kotlinx.coroutines.flow.Flow

interface LocalHelper {
    suspend fun getJokes(): Flow<List<JokesModel>>
    suspend fun deleteAll()
    suspend fun insertAll(model: JokesModel)
    suspend fun getCount(): Flow<Int>
    suspend fun getAllEntries():List<JokesModel>
    suspend fun deleteJokes(jokes:List<JokesModel>)
    suspend fun getLastTenJokes(): List<JokesModel>
}