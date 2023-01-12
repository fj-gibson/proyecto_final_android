package com.francisco.proyectofinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.francisco.proyectofinal.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}