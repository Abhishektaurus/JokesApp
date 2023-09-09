package com.app.jokeapp.jokeui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jokeapp.data.remote.JokesRemoteRepository
import com.app.jokeapp.model.JokesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: JokesRemoteRepository) :
    ViewModel() {

    private var job: Job? = null
    private val _jokeList = MutableStateFlow<List<JokesModel>>(emptyList())
    val jokeList
        get() = _jokeList

    init {
        fetchJoke()
    }

    /**
     * Fetches the jokes from local database first and then start observing the
     * entries of the database
     */
    private fun fetchJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllJokesFromLocal().collect {
                _jokeList.value = it
            }
        }
        clearDatabaseIfNecessary()
    }

    /**
     * This function will fetch jokes from the API after every minute
     */
    fun fetchUpdate() {
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            while (true) {
                repository.getLatestJokeFromRemote().let {
                    if (it.isSuccessful) {
                        val jokeData = it.body() as JokesModel
                        jokeData.time = System.currentTimeMillis()
                        repository.insertAll(jokeData)
                    } else {
                        Log.d(
                            "Error",
                            it.errorBody().toString()
                        ) // need to handle error state as well
                    }
                }
                delay(1000 * 60)
            }
        }
    }


    /**
     * This function is used to clear database if it is crowded with jokes
     * threshold is 30 for now but can be changed as per the requirements
     */
    private fun clearDatabaseIfNecessary() {
        viewModelScope.launch {
            repository.getEntryCount().collect { count ->
                if (count >= 30) {
                    repository.keepOnlyLast10Entries()
                }
            }
        }
    }


    override fun onCleared() {
        job?.cancel()
        job = null
        super.onCleared()
    }
}