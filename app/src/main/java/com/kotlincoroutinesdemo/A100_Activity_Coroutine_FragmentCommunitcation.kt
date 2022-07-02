package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_a100_coroutine_fragment_communitcation.*
import kotlinx.android.synthetic.main.activity_a99_fragment_communication.*
import kotlinx.android.synthetic.main.activity_a99_fragment_communication.tvData

class A100_Activity_Coroutine_FragmentCommunitcation : AppCompatActivity() {
    val viewModel: A100_ViewModel  by lazy { ViewModelProvider(this).get(A100_ViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Cor","Activity-OnCreate")
        setContentView(R.layout.activity_a100_coroutine_fragment_communitcation)
        btnStart.setOnClickListener(View.OnClickListener {
            viewModel.startCoroutine()
        })
        viewModel.getCount().observe(this, Observer {
            tvData.text = it
        })
    }
}