package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class A06_Activity : AppCompatActivity() {
    //intesion of this sample is diff b/w launch & runBlocking
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a06)
        /*lifecycleScope.launch {//doesn't Block the thread on which it is called.
            for(i in 1..10){
                Log.i("Coroutine","value : ${i}")
                delay(1000)
            }
        }
        Log.i("Coroutine","EOP")*/
        /*lifecycleScope.async { //doesn't Block the thread on which it is called.
            for(i in 1..10){
                Log.i("Coroutine","value : ${i}")
                delay(1000)
            }
        }
        Log.i("Coroutine","EOP")*/

        runBlocking { //Block the thread on which it is called.
            for(i in 1..10){
                Log.i("Coroutine","value : ${i}")
                delay(1000)
            }
        }
        Log.i("Coroutine","EOP")
    }
}