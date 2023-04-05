package com.realtimecoroutineretrofit.exception_exceptionhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.model.ApiUser
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ExceptionHandlerViewModel(private val apiHelper: ApiHelper) : ViewModel(){
    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()
    private val exceptionHandler = CoroutineExceptionHandler{ _,e ->
        uiState.postValue(UiState.Error("exception handler: ${e.message}"))
    }
    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch(exceptionHandler) {
            uiState.postValue(UiState.Loading)
            val usersFromApi = apiHelper.getUsersWithError()
            uiState.postValue(UiState.Success(usersFromApi,"Try-Catch"))
        }
    }
    fun getUiState(): LiveData<UiState<List<ApiUser>>> {
        return uiState
    }

}