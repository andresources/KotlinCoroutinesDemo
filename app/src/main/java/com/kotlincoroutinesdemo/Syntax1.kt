package com.kotlincoroutinesdemo

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Syntax1 {
    fun f1() = runBlocking {//Runs main thread
        Log.i("THread",""+Thread.currentThread().name);
        launch {//Runs main thread
            Log.i("THread","Inner1"+Thread.currentThread().name);
        }
        launch {//Runs main thread
            Log.i("THread","Inner2"+Thread.currentThread().name);
        }
    }

    suspend fun f2(time: Long){
        delay(time)
    }
}