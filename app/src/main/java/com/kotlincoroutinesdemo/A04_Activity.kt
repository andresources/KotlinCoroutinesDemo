package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a04.*
import kotlinx.coroutines.*

class A04_Activity : AppCompatActivity() {
    lateinit var my_job:Deferred<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a04)
        btnStart.setOnClickListener(View.OnClickListener {
            my_job= lifecycleScope.async {
                myFun()
                "My Cooo"
            }
            my_job.invokeOnCompletion {
                runBlocking {
                    if(my_job.isCancelled)
                    {
                        Log.i("Coroutine","Coroutine is cancelled...")
                    }

                    if(my_job.isCompleted)
                    {
                        Log.i("Coroutine","Coroutine is completed...")
                    }
                }
            }
        })
        btnPause.setOnClickListener(View.OnClickListener {
            runBlocking {
                stopCoroutine()
            }
        })
        btnAlive.setOnClickListener(View.OnClickListener {
            runBlocking {
                isAliveCor()
            }
        })


    }
    suspend fun stopCoroutine(){
        my_job.cancelAndJoin()
    }
    suspend fun isAliveCor(){
        runBlocking {
            Log.i("Coroutine",""+my_job.isActive)
        }
    }
    suspend fun myFun(){
        for(i in 1..10){
            Log.i("Coroutine","value${i}")
            delay(1000)
        }
    }
}