package com.app.jokeapp.data.remote

import com.app.jokeapp.model.JokesModel
import retrofit2.Response
import javax.inject.Inject

class RemoteApiHelperImpl @Inject constructor(private val apiClientService: ApiClientService):
    RemoteApiHelper {

    override suspend fun getLatestJokeFromRemote(format: String): Response<JokesModel> {
        return apiClientService.getLatestJoke(format)
    }
}