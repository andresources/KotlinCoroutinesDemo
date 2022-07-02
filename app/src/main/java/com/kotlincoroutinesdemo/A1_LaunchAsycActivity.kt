package com.kotlincoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a1_launch_asyc.*
import kotlinx.coroutines.*

class A1_LaunchAsycActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a1_launch_asyc)
        var viewModel: A1_ViewModel = ViewModelProvider(this).get(A1_ViewModel::class.java)
        var viewModel1: A1ViewModel = ViewModelProvider(this).get(A1ViewModel::class.java)
        viewModel1.setText()
        viewModel1.getText()?.observe(this,{
            textView.setText(it)
        })
        button2.setOnClickListener({
            //viewModel.withContextFun(textView)
            //Log.i("Time","MainActivity...")
            var x=Intent(applicationContext,A2_ScopesActivity::class.java)
            startActivity(x)
        })
       /*
        GlobalScope.launch {
            Log.i("Thread","launch : "+Thread.currentThread().name)
            delay(5000)
        }
        GlobalScope.async {
            Log.i("Thread","async : "+Thread.currentThread().name)
            delay(3000)
        }
        Log.i("Thread","Main : "+Thread.currentThread().name)
        */
    }
}