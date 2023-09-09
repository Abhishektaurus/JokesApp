package com.app.jokeapp.di

import android.content.Context
import androidx.room.Room
import com.app.jokeapp.data.local.JokeAppDao
import com.app.jokeapp.data.local.JokesAppDatabase
import com.app.jokeapp.data.local.LocalHelper
import com.app.jokeapp.data.local.LocalHelperImpl
import com.app.jokeapp.data.remote.ApiClientService
import com.app.jokeapp.data.remote.RemoteApiHelper
import com.app.jokeapp.data.remote.RemoteApiHelperImpl
import dagger.Module
import com.app.jokeapp.BuildConfig
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideJokesAppDatabase(@ApplicationContext app: Context): JokesAppDatabase {
        return Room.databaseBuilder(
            app,
            JokesAppDatabase::class.java,
            "jokes_db"
        ).build()
    }

    @Provides
    fun provideJokesAppDao(jokesAppDatabase: JokesAppDatabase):JokeAppDao{
        return jokesAppDatabase.jokesAppDao()
    }

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideHttpClient()=
         OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        BaseUrl:String
    ):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRemoteHelper(apiHelperImpl: RemoteApiHelperImpl) : RemoteApiHelper = apiHelperImpl

    @Provides
    fun provideLocalHelper(localHelperImpl: LocalHelperImpl) : LocalHelper = localHelperImpl

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiClientService {
        return retrofit.create(ApiClientService::class.java)
    }
}