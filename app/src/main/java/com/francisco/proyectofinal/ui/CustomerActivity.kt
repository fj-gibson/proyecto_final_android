package com.francisco.proyectofinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.francisco.proyectofinal.databinding.ActivityCustomerBinding
import com.francisco.proyectofinal.databinding.ActivityHomeBinding

class CustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}