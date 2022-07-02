package com.kotlincoroutinesdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*


class MyApp : Application() {
    init {
        instance = this
    }
    private var x:Int=0
    private var tex = MutableLiveData<CharSequence>()
    companion object{
        private var instance: MyApp? = null
        fun getInstance() : MyApp {
            return instance as MyApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(CoroutineName("GloalCo")+Dispatchers.Main) {
            while (true){
                delay(500)
                x = x+1
                tex.postValue(""+x)
                Log.i("Gloal","Running : "+x+" Thread Name : "+Thread.currentThread())
            }
        }
    }

    fun setIncCount() {
         x = 0;
    }

     fun getText(): LiveData<CharSequence>? {
        return tex
    }
    fun getIncNumer():Int{
        return x
    }
}