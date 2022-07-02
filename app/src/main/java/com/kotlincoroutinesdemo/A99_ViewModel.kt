package com.kotlincoroutinesdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class A99_ViewModel : ViewModel() {
    private val data = MutableLiveData<String>()
    fun setData(selData:String){
        data.value= selData
    }
    fun getData() : LiveData<String>{
        return data
    }
}