package com.example.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.work.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOneTimeRequest = findViewById<Button>(R.id.btnOneTimeRequest)
        val tvOneTimeRequest = findViewById<TextView>(R.id.tvOneTimeRequest)

        btnOneTimeRequest.setOnClickListener{
            val oneTimeRequestConstraint = Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
            val data = Data.Builder()
            data.putString("inputKey","input value")

            val sampleWork = OneTimeWorkRequest
                .Builder(OneTimeRequestWorker::class.java)
                .setInputData(data.build())
                .setConstraints(oneTimeRequestConstraint)
                .build()

            WorkManager.getInstance(this).enqueue(sampleWork)
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(sampleWork.id)
                .observe(this, { workInfo ->
                    OneTimeRequestWorker.Companion.logger(workInfo.state.name)
                    if(workInfo != null){
                        when(workInfo.state){
                            WorkInfo.State.ENQUEUED -> {
                                tvOneTimeRequest.text = "Task Enqueued."
                            }
                            WorkInfo.State.BLOCKED -> {
                                tvOneTimeRequest.text = "Task Blocked."
                            }
                            WorkInfo.State.RUNNING -> {
                                tvOneTimeRequest.text = "Task Running."
                            }
                            else ->{
                                tvOneTimeRequest.text = "Task state else part."
                            }
                        }
                    }

                    if(workInfo != null && workInfo.state.isFinished){
                        when(workInfo.state){
                            WorkInfo.State.SUCCEEDED -> {
                                tvOneTimeRequest.text = "Task successful."

                                //Pegar os dados de saida(output data)
                                val successOutputData = workInfo.outputData
                                val outputText = successOutputData.getString("outputKey")
                                Log.i("Worker Output","$outputText")
                            }
                            WorkInfo.State.FAILED -> {
                                tvOneTimeRequest.text = "Task Failed."
                            }
                            WorkInfo.State.CANCELLED -> {
                                tvOneTimeRequest.text = "Task Cancelled."
                            }
                        }
                    }
                })
        }


    }
}