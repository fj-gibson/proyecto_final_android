package com.francisco.proyectofinal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.francisco.proyectofinal.databinding.ActivityHomeBinding
import com.francisco.proyectofinal.util.PreferenceHelper
import com.francisco.proyectofinal.util.PreferenceHelper.set

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnlogout.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        createSessionPreference()
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun createSessionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = ""
    }
}