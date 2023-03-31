package com.kotlincoroutinesdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class A00_Activity : AppCompatActivity() {
    private lateinit var btnCheckMainThread:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a00)
        Log.i("Corountine",this.localClassName+" is running on thread : "+Thread.currentThread().name+"(TID:"+Thread.currentThread().id+")")
        btnCheckMainThread = findViewById(R.id.btnCheckMainThread)
        btnCheckMainThread.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,A01_Activity::class.java))
        })
    }
}