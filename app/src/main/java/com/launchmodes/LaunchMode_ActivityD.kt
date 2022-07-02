package com.launchmodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlincoroutinesdemo.R
import kotlinx.android.synthetic.main.activity_launch_mode_d.*

class LaunchMode_ActivityD : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_mode_d)
        tvTitle.setText("Activity - D")
        btnA.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,LaunchMode_ActivityA::class.java))
        })
        btnB.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,LaunchMode_ActivityB::class.java))
        })
        btnC.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,LaunchMode_ActivityC::class.java))
        })
        btnD.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,LaunchMode_ActivityD::class.java))
        })
        tvCount.text ="Activity Count - "+getActivityCount()
    }
}