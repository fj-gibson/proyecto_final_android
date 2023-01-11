package com.francisco.proyectofinal.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.adapters.CustomerAdapter
import com.francisco.proyectofinal.databinding.ActivityCustomerBinding
import com.francisco.proyectofinal.io.response.ApiService
import com.francisco.proyectofinal.model.Customer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(R.string.customers)

        binding.newCustomer.setOnClickListener {
            var intent = Intent(this, NewCustomerActivity::class.java)
            startActivity(intent)
        }

        var retrofit = Retrofit.Builder()
            .baseUrl("https://diplomado.alfraber.com/api/v1/customers/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.customers().enqueue(object : Callback<List<Customer>> {
            override fun onResponse(
                call: Call<List<Customer>>,
                response: Response<List<Customer>>
            ) {
                //Array de clientes
                val customersData = response.body()
                if (customersData != null) {
                    initRecyclerView(customersData)
                }

                Toast.makeText(applicationContext, getString(R.string.chargedCustomers), Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                Toast.makeText(applicationContext, getString(R.string.erroLoadCustomers), Toast.LENGTH_SHORT)
                    .show();
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    private fun initRecyclerView(customersData: List<Customer>) {
        val decoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        binding.recycleCustomer.layoutManager = LinearLayoutManager(this)
        binding.recycleCustomer.adapter = CustomerAdapter(customersData)
        binding.recycleCustomer.addItemDecoration(decoration)
    }
}