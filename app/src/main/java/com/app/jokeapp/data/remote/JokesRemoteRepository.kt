package com.app.jokeapp.data.remote

import com.app.jokeapp.data.local.LocalHelper
import com.app.jokeapp.model.JokesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class JokesRemoteRepository @Inject constructor(
    private val remoteApiHelper: RemoteApiHelper,
    private val localHelper: LocalHelper
) {

    suspend fun insertAll(model: JokesModel) = localHelper.insertAll(model)
    suspend fun deleteAll() = localHelper.deleteAll()
    suspend fun getAllJokesFromLocal(): Flow<List<JokesModel>> = localHelper.getJokes()
    suspend fun getLatestJokeFromRemote(): Response<JokesModel> =
        remoteApiHelper.getLatestJokeFromRemote("json")

    suspend fun getEntryCount() = localHelper.getCount()

    suspend fun keepOnlyLast10Entries() {
        withContext(Dispatchers.IO) {
            val last10Entries = localHelper.getLastTenJokes()

            val entriesToDelete = localHelper.getAllEntries().filterNot { it in last10Entries }
            localHelper.deleteJokes(entriesToDelete)
        }
    }

}