package com.francisco.proyectofinal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityHomeBinding
import com.francisco.proyectofinal.util.PreferenceHelper
import com.francisco.proyectofinal.util.PreferenceHelper.set

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCustomer.setOnClickListener {
            var intent = Intent(
                this,
                CustomerActivity::class.java
            )
            startActivity(intent)

        }

        binding.btnAppointment.setOnClickListener {
            var intent = Intent(
                this,
                AppointmentActivity::class.java
            )
            startActivity(intent)

        }

        binding.btnlogout.setOnClickListener {
            logout()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.goCustomers -> {
                var intent = Intent(this, CustomerActivity::class.java)
                startActivity(intent)
            }
            R.id.goAppointments -> {
                var intent = Intent(this, AppointmentActivity::class.java)
                startActivity(intent)
            }
            R.id.goHome -> Toast.makeText(this,"Vamos con citas",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
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