package com.kotlincoroutinesdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class A1ViewModel : ViewModel(){
    private val text = MutableLiveData<CharSequence>()
    fun setText() {
        viewModelScope.launch {
            while (true){
                delay(500)
                text.value = ""+MyApp.getInstance().getIncNumer()
            }
        }
    }
    fun getText(): LiveData<CharSequence>? {
        return text
    }
}