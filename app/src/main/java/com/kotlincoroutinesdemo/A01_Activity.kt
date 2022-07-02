package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class A01_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a01)
        Log.i("Corountine",this.localClassName+" is running on thread : "+Thread.currentThread().name+"(TID:"+Thread.currentThread().id+")")
    }
}