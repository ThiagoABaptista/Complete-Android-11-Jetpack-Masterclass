package com.example.workmanagerdemo

import android.content.Context
import android.content.ContextParams
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class PeriodicRequestWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        val date = getDate(System.currentTimeMillis())
        Log.i("Periodic WorkRequest","doWork Execution DateTime: $date")
        return Result.success()

    }

    private fun getDate(milliseconds: Long): String{

        //Criar um objeto DateFormatter para mostrar a data em um formato especifico
        val formatter = SimpleDateFormat("dd/mm/yyyy hh:mm:ss.SSS", Locale.getDefault())

        //Criar um objeto calendar que irá converter a data e o tempo em milisegundos para
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        return formatter.format(calendar.time)
    }
}