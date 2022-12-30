package com.francisco.proyectofinal.io

import com.francisco.proyectofinal.model.User

data class LoginResponse(
    val token:String,
    val user: User,
    val success:Boolean
)
