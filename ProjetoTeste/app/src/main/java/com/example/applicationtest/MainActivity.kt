package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    /*
    * Um exemplo para mostrar o Activity LifeCycle
    * Ele causa um erro que é possivel ver no Logcat
    * Sempre que um dos metodos são chamados
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("onCreate method","is called...")
    }

    override fun onStart() {
        super.onStart()
        Log.e("onCreate method","is called...")
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume method","is called...")
    }

    override fun onPause() {
        super.onPause()
        Log.e("onPause method","is called...")
    }

    override fun onStop() {
        super.onStop()
        Log.e("onStop method","is called...")
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy method","is called...")
    }
}