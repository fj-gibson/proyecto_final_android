package com.francisco.proyectofinal.model

data class Appointment (
    val id: Int,
    val day: String,
    val room: String,
    val note: String,
    val customer_id: String,
    val status: Int,
    val customer : Customer
)