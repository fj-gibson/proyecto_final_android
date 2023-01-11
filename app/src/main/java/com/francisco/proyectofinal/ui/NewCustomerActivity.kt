package com.francisco.proyectofinal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityNewCustomerBinding
import com.francisco.proyectofinal.io.LoginResponse
import com.francisco.proyectofinal.io.NewCustomer
import com.francisco.proyectofinal.io.response.ApiService
import com.francisco.proyectofinal.model.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCustomerBinding
    private val apiService:ApiService by lazy {
        ApiService.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(R.string.newCustomers)

        binding.btnCreate.setOnClickListener {
            var contador = 0
            if (binding.etNewCustomerName.text.toString() == "") {
                contador++
            }
            if (binding.etNewCustomerEmail.text.toString() == "") {
                contador++
            }
            if (binding.etNewCustomerPhone.text.toString() == "") {
                contador++
            }
            if (contador > 0) {
                Toast.makeText(this, getString(R.string.requireAllFields), Toast.LENGTH_SHORT).show()
            } else {
                val call = apiService.createCustomer(
                    binding.etNewCustomerName.text.toString(),
                    binding.etNewCustomerEmail.text.toString(),
                    binding.etNewCustomerPhone.text.toString()
                )
                call.enqueue(object: retrofit2.Callback<NewCustomer>{
                    override fun onResponse(
                        call: Call<NewCustomer>,
                        response: Response<NewCustomer>
                    ) {
                        if(!response.isSuccessful()){
                            Toast.makeText(binding.etNewCustomerName.context, "El email ya existe", Toast.LENGTH_SHORT).show()
                        }else{
                            var intent = Intent(binding.etNewCustomerName.context, CustomerActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    }

                    override fun onFailure(call: Call<NewCustomer>, t: Throwable) {
                        Log.e("RETROFIT_ERROR", t.toString())
                    }

                })
            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this, CustomerActivity::class.java)
        startActivity(intent)
        finish()
        return false
    }
}