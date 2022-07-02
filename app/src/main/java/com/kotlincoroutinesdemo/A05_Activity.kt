package com.kotlincoroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_a05.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class A05_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a05)

        btnNetwork.setOnClickListener(View.OnClickListener {
            readData() //will crash
        })
        btnNetworkUsingThread.setOnClickListener(View.OnClickListener {
            Thread{
                readDataUpdateUI()
            }.start()
        })
        btnNetworkCoroutine.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                readDataUsingCor()
                //tv.text = "RCode : "+responseCode -> will cause error->updating ui from io thread
                runBlocking(Dispatchers.Main) {
                    tv.text = "RCode : "+responseCode
                }
            }
        })
        btnWithContext.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    readDataUsingCor()
                }
                withContext(Dispatchers.Main){
                    tv.text = "RCode : "+responseCode
                }
            }
        })
    }

     var responseCode:Int?=null
    suspend fun readDataUsingCor(){
        var url = URL("https://www.sakshi.com/")
        var urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        responseCode = urlConnection.getResponseCode()
        //tv.text = "RCode : "+responseCode -> Error bz updating UI FROM Main Thread
    }
     fun readData(){
        var url = URL("https://www.sakshi.com/")
        var urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
         responseCode = urlConnection.getResponseCode()
    }
    fun readDataUpdateUI(){
        var url = URL("https://www.sakshi.com/")
        var urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        responseCode = urlConnection.getResponseCode()
        runOnUiThread{
            tv.text = "RCode : "+responseCode
        }
    }
}