package com.kotlincoroutinesdemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class A100_ViewModel : ViewModel() {
    private val data = MutableLiveData<String>()
    fun startCoroutine(){
        viewModelScope.launch() {
            for (i in 1..100){
                Log.i("Cor","${i}")
                delay(1000)
                data.value = i.toString()
            }
        }
    }

    fun getCount() : LiveData<String>{
        return data
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Cor","View Model is cleared...")
    }
}