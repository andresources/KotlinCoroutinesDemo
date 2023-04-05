package com.realtimecoroutineretrofit.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UiState
import com.realtimecoroutineretrofit.ApiHelper
import com.realtimecoroutineretrofit.model.ApiUser
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SingleNetworkCallViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val uiState = MutableLiveData<UiState<List<ApiUser>>>()
    init {
        fetchUsers()
    }

    private fun fetchUsers(){
        viewModelScope.launch {
            coroutineScope {
                uiState.postValue(UiState.Loading)
                try {
                    val apiUsers = apiHelper.getUsers()
                    uiState.postValue(UiState.Success(apiUsers,"Success from ViewModel"))
                } catch (e: Exception) {
                    uiState.postValue(UiState.Error(e.toString()))
                }
            }
        }
    }

    fun getUiState() : LiveData<UiState<List<ApiUser>>>  = uiState
}