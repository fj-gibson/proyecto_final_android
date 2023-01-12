package com.francisco.proyectofinal.io.response

import android.content.SharedPreferences
import com.francisco.proyectofinal.io.LoginResponse
import com.francisco.proyectofinal.io.NewAppointment
import com.francisco.proyectofinal.io.NewCustomer
import com.francisco.proyectofinal.model.Appointment
import com.francisco.proyectofinal.model.Customer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {


    @POST(value = "auth/login")
    fun postLogin(
        @Query(value = "email") email: String,
        @Query(value = "password") password: String
    ):
            Call<LoginResponse>

    companion object Factory {
        private const val BASE_URL = "https://diplomado.alfraber.com/api/v1/"
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET(value = "get")
    fun customers(): Call<List<Customer>>

    @POST("https://diplomado.alfraber.com/api/v1/customers/create")
    fun createCustomer(
        @Query(value = "name") name: String,
        @Query(value = "email") email: String,
        @Query(value = "phone") phone: String
    ): Call<NewCustomer>

    @DELETE("https://diplomado.alfraber.com/api/v1/customers/delete")
    fun deleteCustomer(@Query(value = "id") name: String): Call<NewCustomer>

    @GET(value = "get")
    fun appointments(): Call<List<Appointment>>

    @DELETE("https://diplomado.alfraber.com/api/v1/appointment/delete")
    fun deleteAppointment(@Query(value = "id") name: Int): Call<Appointment>

    @POST("https://diplomado.alfraber.com/api/v1/appointment/create")
    fun createAppointment(
        @Query(value = "email") email: String,
        @Query(value = "room") room: String,
        @Query(value = "date") date: String,
        @Query(value = "note") note: String
    ): Call<NewAppointment>


}