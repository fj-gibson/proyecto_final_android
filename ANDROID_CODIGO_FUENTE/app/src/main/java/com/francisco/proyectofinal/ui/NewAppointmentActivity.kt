package com.francisco.proyectofinal.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityNewAppointmentBinding
import com.francisco.proyectofinal.io.NewAppointment
import com.francisco.proyectofinal.io.NewCustomer
import com.francisco.proyectofinal.io.response.ApiService
import retrofit2.Call
import retrofit2.Response


class NewAppointmentActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityNewAppointmentBinding
    private val apiService: ApiService by lazy {
        ApiService.create()
    }
    var radio1: RadioButton? = null
    var radio2: RadioButton? = null
    var radio3: RadioButton? = null
    var radioGroup: RadioGroup? = null
    var radioSelectData: String = "salón 1"
    var dateSelectData: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(getString(R.string.newAppointment));

        binding.etNewAppointmentDay.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnCreate.setOnClickListener {
            radio1 = binding.rbR1
            radio2 = binding.rbR2
            radio3 = binding.rbR3
            radioGroup = binding.rdRoomNewCustomer
            radioGroup?.setOnCheckedChangeListener(this)
            var contador = 0
            if (binding.etNewCustomerEmail.text.toString() == "") {
                binding.etNewCustomerEmail.setError(getString(R.string.RequireField))
                contador++
            }
            if (radioSelectData == "") {
                contador++
            }
            if (dateSelectData == "") {
                binding.etNewAppointmentDay.setError(getString(R.string.RequireField))
                contador++
            }

            if (contador > 0) {
                Toast.makeText(this, getString(R.string.SomeFIeldNull), Toast.LENGTH_SHORT).show()
            } else {
                val call = apiService.createAppointment(
                    binding.etNewCustomerEmail.text.toString(),
                    radioSelectData,
                    dateSelectData,
                    binding.etNewAppointmentNote.text.toString()
                )
                call.enqueue(object : retrofit2.Callback<NewAppointment> {
                    override fun onResponse(
                        call: Call<NewAppointment>,
                        response: Response<NewAppointment>
                    ) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(
                                binding.etNewCustomerEmail.context,
                                "El email no existe",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                binding.etNewCustomerEmail.context,
                                "Cita creada correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            var intent = Intent(
                                binding.etNewCustomerEmail.context,
                                AppointmentActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        }

                    }

                    override fun onFailure(call: Call<NewAppointment>, t: Throwable) {
                        Toast.makeText(
                            binding.etNewCustomerEmail.context,
                            "Error al crear cita",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("ERROR:-", t.toString())
                    }


                })
            }
        }
    }


    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etNewAppointmentDay.setText(" $day-${month + 1}-$year")
        dateSelectData = "$year-$month-$day"

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        when (idRadio) {
            radio1?.id -> radioSelectData = "salón 1"
            radio2?.id -> radioSelectData = "salón 2"
            radio3?.id -> radioSelectData = "salón 3"

        }
    }
}