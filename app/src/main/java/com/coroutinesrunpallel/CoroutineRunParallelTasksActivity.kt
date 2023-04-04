package com.coroutinesrunpallel

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kotlincoroutinesdemo.R

class CoroutineRunParallelTasksActivity : AppCompatActivity() {
    private lateinit var pb: ProgressBar
    private lateinit var tv: TextView
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_run_parallel_tasks)
        pb = findViewById(R.id.pb)
        tv = findViewById(R.id.tv)
        setupViewModel()
        setupLongRunningTask()
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }
    private fun setupLongRunningTask() {
        viewModel.getUiState().observe(this) {
            it?.let {
                when (it) {
                is UiState.Success -> {
                    pb.visibility = View.GONE
                    tv.text = it.data
                    tv.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    pb.visibility = View.VISIBLE
                    tv.visibility = View.GONE
                }
                is UiState.Error -> {
                    //Handle Error
                    pb.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
           }
        }
        viewModel.startLongRunningTask()
    }
}