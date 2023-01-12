package com.francisco.proyectofinal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.adapters.AppointmentAdapter
import com.francisco.proyectofinal.adapters.CustomerAdapter
import com.francisco.proyectofinal.databinding.ActivityAppointmentBinding
import com.francisco.proyectofinal.io.response.ApiService
import com.francisco.proyectofinal.model.Appointment
import com.francisco.proyectofinal.model.Customer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(R.string.appointment);

        binding.newAppointment.setOnClickListener {
            var intent = Intent(this, NewAppointmentActivity::class.java)
            startActivity(intent)
        }

        var retrofit = Retrofit.Builder()
            .baseUrl("https://diplomado.alfraber.com/api/v1/appointment/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.appointments().enqueue(object : Callback<List<Appointment>> {
            override fun onResponse(
                call: Call<List<Appointment>>,
                response: Response<List<Appointment>>
            ) {
                //Array de clientes
                val appointmentData = response.body()
                if (appointmentData != null) {
                    initRecyclerView(appointmentData)
                }
                Toast.makeText(
                    applicationContext,
                    getString(R.string.LoadAppointments),
                    Toast.LENGTH_SHORT
                ).show();
            }

            override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.ErrorLoadAppointments),
                    Toast.LENGTH_SHORT
                )
                    .show();
            }


        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    private fun initRecyclerView(appointmentData: List<Appointment>) {
        val decoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        binding.recycleAppointment.layoutManager = LinearLayoutManager(this)
        binding.recycleAppointment.adapter = AppointmentAdapter(appointmentData)
        binding.recycleAppointment.addItemDecoration(decoration)
    }
}