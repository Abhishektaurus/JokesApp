package com.app.jokeapp.data.local

import com.app.jokeapp.model.JokesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalHelperImpl @Inject constructor(private val dao: JokeAppDao): LocalHelper {
    override suspend fun getJokes(): Flow<List<JokesModel>> {
        return dao.getLastJokes()
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun insertAll(model: JokesModel) {
        dao.insertResult(model)
    }

    override suspend fun getCount(): Flow<Int> {
        return dao.getCount()
    }

    override suspend fun getAllEntries(): List<JokesModel> {
        return dao.getAll()
    }

    override suspend fun deleteJokes(jokes:List<JokesModel>) {
        dao.deleteJokes(jokes)
    }

    override suspend fun getLastTenJokes(): List<JokesModel> {
       return dao.getLast10Jokes()
    }
}