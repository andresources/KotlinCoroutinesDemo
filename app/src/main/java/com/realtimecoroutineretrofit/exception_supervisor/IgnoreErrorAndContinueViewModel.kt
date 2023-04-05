package com.realtimecoroutineretrofit.exception_supervisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.model.ApiUser
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class IgnoreErrorAndContinueViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        // supervisorScope is needed, so that we can ignore error and continue
        // here, more than two child jobs are running in parallel under a supervisor, one child job gets failed, we can continue with other.
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            supervisorScope {
                val usersFromApiDeferred = async { apiHelper.getUsersWithError() }
                val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }
                val usersFromApi = try {
                    usersFromApiDeferred.await()
                }catch (e : Exception){
                    emptyList<ApiUser>()
                }
                val moreUsersFromApi = try {
                    moreUsersFromApiDeferred.await()
                }catch (e : Exception){
                    emptyList<ApiUser>()
                }
                val allUsersFromApi = mutableListOf<ApiUser>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)

                uiState.postValue(UiState.Success(allUsersFromApi,""))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> = uiState
}