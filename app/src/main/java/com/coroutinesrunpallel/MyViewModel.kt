package com.coroutinesrunpallel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MyViewModel : ViewModel() {

    private val uiState = MutableLiveData<UiState<String>>()

    fun startLongRunningTask() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                // do long running tasks
                val resultOneDeferred = async { doLongRunningTaskOne() }
                val resultTwoDeferred = async { doLongRunningTaskTwo() }
                var combinedResult:Int = 0
                val timeTaken = measureTimeMillis {
                    combinedResult = resultOneDeferred.await() + resultTwoDeferred.await()
                }
                uiState.postValue(UiState.Success("Task Completed : $combinedResult and time taken : $timeTaken",""))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<String>> {
        return uiState
    }

    private suspend fun doLongRunningTaskOne(): Int {
        return withContext(Dispatchers.Default) {
            // your code for doing a long running task
            // Added delay to simulate
            delay(5000)
            return@withContext 10
        }
    }

    private suspend fun doLongRunningTaskTwo(): Int {
        return withContext(Dispatchers.Default) {
            // your code for doing a long running task
            // Added delay to simulate
            delay(2000)
            return@withContext 10
        }
    }

}