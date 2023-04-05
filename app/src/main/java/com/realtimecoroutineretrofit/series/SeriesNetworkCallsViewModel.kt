package com.realtimecoroutineretrofit.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.model.ApiUser
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SeriesNetworkCallsViewModel(private val apiHelper: ApiHelper) : ViewModel(){
    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            val allUsersFromApi = mutableListOf<ApiUser>()
            try {
                val usersFromApi = apiHelper.getUsers()
                val moreUsersFromApi = apiHelper.getMoreUsers()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)
                uiState.postValue(UiState.Success(allUsersFromApi,"data is fetched."))

            }catch (e: java.lang.Exception){
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }
}