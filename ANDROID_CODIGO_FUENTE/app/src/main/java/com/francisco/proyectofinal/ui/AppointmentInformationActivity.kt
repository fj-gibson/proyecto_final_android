package com.francisco.proyectofinal.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityAppointmentInformationBinding
import com.francisco.proyectofinal.io.NewCustomer
import com.francisco.proyectofinal.io.response.ApiService
import com.francisco.proyectofinal.model.Appointment
import retrofit2.Call
import retrofit2.Response

class AppointmentInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentInformationBinding
    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(getString(R.string.AppointmentInformation))
        val customerEmail: String? = intent.getStringExtra("customer_email")
        val customerName: String? = intent.getStringExtra("customer_name")
        val customerPhone: String? = intent.getStringExtra("customer_phone")
        val customerPhoto: String? = intent.getStringExtra("customer_photo")
        val day: String? = intent.getStringExtra("day")
        val room: String? = intent.getStringExtra("room")
        val note: String? = intent.getStringExtra("note")
        val status: Int = intent.getIntExtra("status",1)
        val id: Int = intent.getIntExtra("id",0)
        binding.tvDayAppointment.text = day
        binding.tVRoomAppointment.text = room
        binding.tvNoteAppointment.text = "Nota: "+note
        if(status==1){
            binding.tvStatusAppointment.text = "Confirmada"
            binding.tvStatusAppointment.setTextColor(Color.parseColor("#00e81e"))
        }
        if(status==2){
            binding.tvStatusAppointment.text = "Sin confirmar"
            binding.tvStatusAppointment.setTextColor(Color.parseColor("#e8003d"))
        }

        binding.tvCustomerName.text = customerName
        binding.tvCustomerEmail.text = customerEmail
        binding.tvCustomerPhone.text = customerPhone
        Glide.with(this).load(customerPhoto).into(binding.ivCustomerPhoto)

        binding.btnDeleteCustomer.setOnClickListener {
            val call = id?.let { it1 ->
                apiService.deleteAppointment(
                    it1
                )
            }
            if (call != null) {
                call.enqueue(object: retrofit2.Callback<Appointment>{
                    override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {
                        Log.e("Response",response.toString())
                        if(!response.isSuccessful()){
                            Toast.makeText(binding.tvCustomerName.context, getString(R.string.AppointmentNotFound), Toast.LENGTH_SHORT).show()
                        }else{
                            var intent = Intent(binding.tvCustomerName.context, AppointmentActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<Appointment>, t: Throwable) {
                        Toast.makeText(applicationContext, getString(R.string.ErrorDeleteCustomer), Toast.LENGTH_SHORT)
                            .show();
                    }

                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, AppointmentActivity::class.java)
        startActivity(intent)
        finish()
        return false
    }
}