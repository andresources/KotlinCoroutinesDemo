package com.kotlincoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a09.*
import kotlinx.coroutines.*

class A09_Activity : AppCompatActivity() {
    //Diff. Scopes of Coroutines- Global,lifecycle Scope,

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a09)
        btnLaunchCorNormal.setOnClickListener(View.OnClickListener {
            funLaunchCorNormal()
        })
        btnLaunchCorGlobalScope.setOnClickListener(View.OnClickListener {
            funLaunchCorGlobalScope()
        })
        btnLaunchCorLifeCycleScope.setOnClickListener(View.OnClickListener {
            funLaunchCorLifeCycleScope()
        })
        btnLaunchCorViewModelScope.setOnClickListener(View.OnClickListener {
            var viewModel: A09ViewModel = ViewModelProvider(this).get(A09ViewModel::class.java)
            viewModel.funViewModelScope()
        })
        btnStartNewActivity.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,A01_Activity::class.java))
            finish()
        })
    }
    fun funLaunchCorNormal(){
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 1..20){
                Log.i("Cor","${i}")
                delay(1500)
            }
        }
    }

    fun funLaunchCorGlobalScope(){
        GlobalScope.launch {
            for (i in 1..20){
                Log.i("Cor","${i}")
                delay(1500)
            }
        }
    }
    fun funLaunchCorLifeCycleScope(){
        lifecycleScope.launch {
            for (i in 1..20){
                Log.i("Cor","${i}")
                delay(1500)
            }
        }
    }
}