package com.app.jokeapp.data.remote

import com.app.jokeapp.model.JokesModel
import retrofit2.Response

interface RemoteApiHelper {
    suspend fun getLatestJokeFromRemote(format:String):Response<JokesModel>
}