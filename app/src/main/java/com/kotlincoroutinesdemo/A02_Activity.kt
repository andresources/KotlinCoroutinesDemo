package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_a02.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class A02_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a02)
        btnStart.setOnClickListener(View.OnClickListener {
            GlobalScope.launch(Dispatchers.Main) {//This code is running on main-ui thread
                val time=measureTimeMillis {
                    //May not Crash, and switch button works
                    fun1()
                    fun2()
                }
                Log.i("Coroutine","Completion Time : ${time}")
            }
        })
        btnNoCo.setOnClickListener(View.OnClickListener {//This code is running on main-ui thread
            //May Crash, and switch button won't work
            fun3()
            fun4()
        })
    }

    suspend fun fun1(){
        for ( i in 1..10){
            Log.i("Coroutine","fun1 : ${i}")
            delay(500)
        }
        Log.i("Coroutine","fun1 - Thread : "+Thread.currentThread().name)
    }

    suspend fun fun2(){
        for ( i in 11..20){
            Log.i("Coroutine","fun2 : ${i}")
            delay(1000)
        }
        Log.i("Coroutine","fun2 - Thread : "+Thread.currentThread().name)
    }
     fun fun3(){
        for ( i in 1..10){
            Log.i("Coroutine","fun3 : ${i}")
            Thread.sleep(1000)
        }
        Log.i("Coroutine","fun3 - Thread : "+Thread.currentThread().name)
    }
     fun fun4(){
        for ( i in 11..20){
            Log.i("Coroutine","fun4 : ${i}")
            Thread.sleep(1000)
        }
        Log.i("Coroutine","fun4 - Thread : "+Thread.currentThread().name)
    }
}