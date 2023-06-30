package com.coroutinescope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.kotlincoroutinesdemo.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineCompletionActivity : AppCompatActivity() {
    private lateinit var tvJob1:TextView
    private lateinit var tvJob2:TextView
    private lateinit var tvJob3:TextView
    private lateinit var tvFinalRes:TextView

    private lateinit var pbJob1:ProgressBar
    private lateinit var pbJob2:ProgressBar
    private lateinit var pbJob3:ProgressBar

    private lateinit var parentJob: Job
    private lateinit var job1: Job
    private lateinit var job2: Job
    private lateinit var job3: Job

    var myScope = getMyScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_completion)
        tvJob1 = findViewById(R.id.tvJob1)
        tvJob2 = findViewById(R.id.tvJob2)
        tvJob3 = findViewById(R.id.tvJob3)
        tvFinalRes = findViewById(R.id.tvFinalRes)

        pbJob1 = findViewById(R.id.pbJob1)
        pbJob2 = findViewById(R.id.pbJob2)
        pbJob3 = findViewById(R.id.pbJob3)
    }

    fun funLaunchAllCors(view: View) {
        parentJob = myScope.launch {
            tvFinalRes.setText("All Tasks Started..")
            job1 = launch {
                var i = 0
                var fg = true
                while (fg){
                    i++
                    delay(1500L)
                    tvJob1.setText("Job1(Status-$isActive) : $i")
                    if (i==5) fg=false
                }
            }
            job2 = launch {
                var i = 0
                var fg = true
                while (fg){
                    i++
                    delay(2000L)
                    tvJob2.setText("Job2(Status-$isActive) : $i")
                    if (i==5) fg=false
                }
            }
            job3 = launch {
                var i = 0
                var fg = true
                while (fg){
                    i++
                    delay(3000L)
                    tvJob3.setText("Job3(Status-$isActive) : $i")
                    if (i==5) fg=false
                }
            }
            job1.invokeOnCompletion {
                pbJob1.visibility = View.GONE
                if(job1.isCancelled){
                    tvJob1.setText("Job1 Cancelled")
                }
                if(!job1.isCancelled){
                    tvJob1.setText("Job1 Completed : ${job1.isActive}")
                }
            }

            job2.invokeOnCompletion {
                pbJob2.visibility = View.GONE
                if(job2.isCancelled){
                    tvJob2.setText("Job2 Cancelled")
                }
                if(!job2.isCancelled){
                    tvJob2.setText("Job2 Completed: ${job2.isActive}")
                }
            }

            job3.invokeOnCompletion {
                pbJob3.visibility = View.GONE
                if(job3.isCancelled){
                    tvJob3.setText("Job3 Cancelled")
                }
                if(!job3.isCancelled){
                    tvJob3.setText("Job3 Completed: : ${job3.isActive}")
                }
            }
            parentJob.invokeOnCompletion {
                if(parentJob.isCancelled){
                    tvFinalRes.setText("Parent Cancelled")
                }
                if(!parentJob.isCancelled){
                    tvFinalRes.setText("Parent Completed")
                }
            }
        }




    }
    fun funStopAllCors(view: View) {
        myScope.cancel()
    }
}