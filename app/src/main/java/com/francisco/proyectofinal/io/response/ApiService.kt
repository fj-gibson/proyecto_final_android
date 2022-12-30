package com.francisco.proyectofinal.io.response

import com.francisco.proyectofinal.io.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST(value="auth/login")
    fun postLogin(@Query(value="email") email: String, @Query(value="password") password: String ):
            Call<LoginResponse>

    companion object Factory{
        private const val BASE_URL = "https://diplomado.alfraber.com/api/v1/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}