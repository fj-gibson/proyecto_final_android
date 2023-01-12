package com.francisco.proyectofinal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ActivityLoginBinding
import com.francisco.proyectofinal.io.LoginResponse
import com.francisco.proyectofinal.io.response.ApiService
import com.francisco.proyectofinal.util.PreferenceHelper
import com.francisco.proyectofinal.util.PreferenceHelper.get
import com.francisco.proyectofinal.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {
    private val apiService:ApiService by lazy {
        ApiService.create()
    }
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["jwt",""].contains(".")){
            sendLogin()
        }
        supportActionBar?.setTitle("Login")
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            checkLogin()
        }
    }

    private fun checkLogin() {
        var email       = binding.etEmail.text.toString()
        var password    = binding.etPassword.text.toString()
        if(binding.etEmail.text.toString().isEmpty() || binding.etPassword.text.toString().isEmpty()){
            alertFail("El email y contraseña son requeridos")
        }else{
            performLogin()
        }
    }

    private fun sendLogin() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun createSessionPreference(token:String){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["jwt"] = token
    }

    private fun alertFail(s: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Datos incorrectos")
            .setIcon(R.drawable.ic_warning)
            .setMessage(s)
            .show()
    }

    private fun performLogin(){
        val email       = binding.etEmail.text.toString()
        val password    = binding.etPassword.text.toString()
        val call        = apiService.postLogin(email,password)
        call.enqueue(object: retrofit2.Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful()){
                    val  loginResponse = response.body()
                    if(loginResponse==null){
                        Toast.makeText(applicationContext,"Error de servidor 0x01",Toast.LENGTH_LONG).show()
                        return
                    }
                    if(loginResponse.success){
                        createSessionPreference(loginResponse.token)
                        sendLogin()
                    }else{
                        Toast.makeText(applicationContext,"Las credenciales son incorrectas",Toast.LENGTH_LONG).show()
                    }

                }else{
                    Log.e("Error Server",response.toString())
                    Toast.makeText(applicationContext,"Las credenciales son incorrectas",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Error Server", t.toString())
                Toast.makeText(applicationContext,"Error de servidor favor de checar conexión 0x03",Toast.LENGTH_LONG).show()
            }

        } )


    }
}