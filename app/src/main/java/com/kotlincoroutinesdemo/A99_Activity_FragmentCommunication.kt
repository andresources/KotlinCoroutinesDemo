package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_a99_fragment_communication.*

class A99_Activity_FragmentCommunication : AppCompatActivity() {
    val viewModel: A99_ViewModel  by lazy { ViewModelProvider(this).get(A99_ViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a99_fragment_communication)
       // var viewModel: A99_ViewModel  = ViewModelProvider(this).get(A99_ViewModel::class.java)
        btnUpdate.setOnClickListener(View.OnClickListener {
            viewModel.setData("I am From Hosting Activity")
        })
        viewModel.getData().observe(this, Observer {
            tvData.text = it
        })
    }
}