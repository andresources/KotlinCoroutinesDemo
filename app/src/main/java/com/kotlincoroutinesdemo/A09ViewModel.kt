package com.kotlincoroutinesdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class A09ViewModel : ViewModel() {
    fun funViewModelScope(){
        viewModelScope.launch() {
            for (i in 1..20){
                Log.i("Cor","${i}")
                delay(1500)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Cor","View Model is cleared...")
    }
}