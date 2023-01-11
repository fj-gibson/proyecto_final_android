package com.francisco.proyectofinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityAppointmentBinding

class AppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(R.string.appointment);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}