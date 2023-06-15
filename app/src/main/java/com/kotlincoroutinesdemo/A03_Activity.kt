package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a003.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class A03_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a003)
        btnCoroutine.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                val time=measureTimeMillis {
                    var j1=async(Dispatchers.Main) {
                        coroutineFun1()
                    }
                    var j2=async(Dispatchers.Main) {
                        coroutineFun2()
                    }
                    j1.await()
                    j2.await()
                }
                Log.i("Coroutine","Com Time : ${time}")
            }
        })
        btnThread.setOnClickListener(View.OnClickListener {
            Thread{ThreadFun1()}.start()
            Thread{ThreadFun2()}.start()
        })
    }

    suspend fun coroutineFun1(){
        for ( i in 1..10){
            Log.i("Coroutine","coroutineFun1 : ${i}")
            delay(1000)
        }
        Log.i("Coroutine","coroutineFun1 - Thread : "+Thread.currentThread().name)
    }

    suspend fun coroutineFun2(){
        for ( i in 11..20){
            Log.i("Coroutine","coroutineFun2 : ${i}")
            delay(1000)
        }
        Log.i("Coroutine","coroutineFun2 - Thread : "+Thread.currentThread().name)
    }

     fun ThreadFun1(){
        for ( i in 1..10){
            Log.i("Coroutine","ThreadFun1 : ${i}")
            Thread.sleep(1000)
        }
        Log.i("Coroutine","ThreadFun1 - Thread : "+Thread.currentThread().name)
    }

     fun ThreadFun2(){
        for ( i in 11..20){
            Log.i("Coroutine","ThreadFun2 : ${i}")
            Thread.sleep(1000)
        }
        Log.i("Coroutine","ThreadFun2 - Thread : "+Thread.currentThread().name)
    }
}