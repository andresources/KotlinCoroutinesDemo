package com.realtimecoroutineretrofit.exception_trycatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.model.ApiUser
import kotlinx.coroutines.launch

class TryCatchViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getUsersWithError()
                uiState.postValue(UiState.Success(usersFromApi,"Try-Catch"))
            }catch (e:Exception){
                uiState.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<ApiUser>>> = uiState
}