package com.francisco.proyectofinal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityCustomerInfoBinding
import com.francisco.proyectofinal.io.NewCustomer
import com.francisco.proyectofinal.io.response.ApiService
import retrofit2.Call
import retrofit2.Response

class CustomerInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerInfoBinding
    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(getString(R.string.CustomerInformation))
        val customerEmail: String? = intent.getStringExtra("cutomer_email")
        val customerName: String? = intent.getStringExtra("cutomer_name")
        val customerPhone: String? = intent.getStringExtra("cutomer_phone")
        val customerPhoto: String? = intent.getStringExtra("cutomer_photo")
        binding.tvCustomerName.text = customerName
        binding.tvCustomerEmail.text = customerEmail
        binding.tvCustomerPhone.text = customerPhone
        Glide.with(this).load(customerPhoto).into(binding.ivCustomerPhoto)
        binding.btnDeleteCustomer.setOnClickListener {
            val call = customerEmail?.let { it1 ->
                apiService.deleteCustomer(
                    it1
                )
            }
            if (call != null) {
                call.enqueue(object: retrofit2.Callback<NewCustomer>{
                    override fun onResponse(call: Call<NewCustomer>, response: Response<NewCustomer>) {
                        Log.e("Response",response.toString())
                        if(!response.isSuccessful()){
                            Toast.makeText(binding.tvCustomerName.context, getString(R.string.CustomerNotFound), Toast.LENGTH_SHORT).show()
                        }else{
                            var intent = Intent(binding.tvCustomerName.context, CustomerActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<NewCustomer>, t: Throwable) {
                        Toast.makeText(applicationContext, getString(R.string.ErrorDeleteCustomer), Toast.LENGTH_SHORT)
                            .show();
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