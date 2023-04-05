package com.realtimecoroutineretrofit.exception_supervisor

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.UiState
import com.ViewModelFactory
import com.kotlincoroutinesdemo.R
import com.realtimecoroutineretrofit.ApiHelperImpl
import com.realtimecoroutineretrofit.RetrofitBuilder
import com.realtimecoroutineretrofit.model.ApiUser
import com.realtimecoroutineretrofit.single.ApiUserAdapter
import com.realtimecoroutineretrofit.single.SingleNetworkCallViewModel

class IgnoreErrorAndContinueActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: IgnoreErrorAndContinueViewModel
    private lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_network_call)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getUiState().observe(this){
            it?.let {
                when(it){
                    is UiState.Loading ->{
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }

                    is UiState.Success ->{
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        renderList(it.data)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }

                    is UiState.Error ->{
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiUser))
        )[IgnoreErrorAndContinueViewModel::class.java]
    }

    private fun setupUI() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation))
        adapter = ApiUserAdapter(arrayListOf())
        recyclerView.adapter = adapter
        progressBar = findViewById(R.id.progressBar)
    }

    private fun renderList(users: List<ApiUser>){
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}