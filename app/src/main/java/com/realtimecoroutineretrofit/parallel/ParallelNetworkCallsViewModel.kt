package com.realtimecoroutineretrofit.parallel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.model.ApiUser
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class ParallelNetworkCallsViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            coroutineScope {
                try {
                    val allUsersFromApi = mutableListOf<ApiUser>()
                    val timeTaken = measureTimeMillis {
                        val usersFromApiDeferred = async { apiHelper.getUsers() }
                        val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }
                        val usersFromApi = usersFromApiDeferred.await()
                        val moreUsersFromApi = moreUsersFromApiDeferred.await()
                        allUsersFromApi.addAll(usersFromApi)
                        allUsersFromApi.addAll(moreUsersFromApi)
                    }
                    uiState.postValue(UiState.Success(allUsersFromApi, "Time taken : $timeTaken"))
                } catch (e: Exception) {
                    uiState.postValue(UiState.Error(e.message.toString()))
                }
            }
        }
    }
    fun getUiState(): LiveData<UiState<List<ApiUser>>> = uiState
}