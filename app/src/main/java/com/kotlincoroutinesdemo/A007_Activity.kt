package com.kotlincoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a007.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class A007_Activity : AppCompatActivity() {
    //intension is lifecycle scope - and thread life cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a007)
        btnLaunchLifecycleScopeCor.setOnClickListener(
            View.OnClickListener {
                funLaunchLifecycleScopeCor()
            })
        btnThread.setOnClickListener(View.OnClickListener {
            funThread()
        })
        btnNewActivity.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,A01_Activity::class.java))
            finish()
        })

    }
    fun funThread(){
        Thread{
            for (i in 1..20){
                Thread.sleep(1000)
                Log.i("Coroutine","${i}")
            }
        }.start()
    }
    fun funLaunchLifecycleScopeCor(){
        var job=lifecycleScope.launch {
            for (i in 1..20){
                delay(1000)
                Log.i("Coroutine","${i}")
            }
        }
        job.invokeOnCompletion {
            if(job.isCancelled){
                Log.i("Coroutine","Coroutinue is cancelled.")
            }
            if(job.isCompleted){
                Log.i("Coroutine","Coroutinue is completed.")
            }
        }
    }
}