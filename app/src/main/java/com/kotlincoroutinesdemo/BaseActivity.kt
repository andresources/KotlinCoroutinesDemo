package com.kotlincoroutinesdemo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_a2_scopes.*

open class BaseActivity : AppCompatActivity() {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        MyApp.getInstance().setIncCount()
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.getInstance().getText()?.observe(this,{
           if(it.equals("100")){
               var x= Intent(applicationContext,A1_LaunchAsycActivity::class.java)
               startActivity(x)
           }
        })
    }
}