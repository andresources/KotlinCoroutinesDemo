package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a2_scopes.*
import kotlinx.coroutines.launch

class A2_ScopesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a2_scopes)
        /*var viewModel1: A1ViewModel = ViewModelProvider(this).get(A1ViewModel::class.java)
        viewModel1.setText()
        viewModel1.getText()?.observe(this,{
            textView2.setText(it)
        })*/
        MyApp.getInstance().getText()?.observe(this,{
            textView2.setText(it)
        })
        button3.setOnClickListener {
        }
    }
}