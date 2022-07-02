package com.kotlincoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a08.*
import kotlinx.coroutines.*

class A08_Activity : AppCompatActivity() {
    //CoroutineScope:
   val mCoroutineScope: CoroutineScope by lazy { CoroutineScope(Dispatchers.Main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a08)
        tvStartCoroutineScope.setOnClickListener(View.OnClickListener {
            startCor()
        })
        tvStopCoroutineScope.setOnClickListener(View.OnClickListener {
            stopCor()
        })
        tvStartNewActivity.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,A01_Activity::class.java))
        })

    }

    fun startCor(){
        mCoroutineScope.launch {
            Log.i("Cor","Cor1 running thread :"+Thread.currentThread().name)
            for(i in 1..20) {
                Log.i("Cor","cor1 : ${i}")
                delay(1000)
            }
        }
        mCoroutineScope.launch {
            Log.i("Cor","Cor2 running thread :"+Thread.currentThread().name)
            for(i in 21..40) {
                Log.i("Cor","cor1 : ${i}")
                delay(1500)
            }
        }
    }
    fun stopCor(){
        if(mCoroutineScope.isActive){
            mCoroutineScope.cancel()
        }
    }

    override fun onPause() {
        super.onPause()
        /*if(mCoroutineScope.isActive){
            mCoroutineScope.cancel()
        }*/
    }

}