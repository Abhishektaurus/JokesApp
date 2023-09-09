package com.app.jokeapp.data.remote

import com.app.jokeapp.model.JokesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClientService {

    @GET("api")
    suspend fun getLatestJoke(@Query("format") format:String): Response<JokesModel>
}