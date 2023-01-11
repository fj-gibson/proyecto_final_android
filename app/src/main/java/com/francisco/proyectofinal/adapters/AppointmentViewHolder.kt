package com.francisco.proyectofinal.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ItemAppointmentBinding
import com.francisco.proyectofinal.model.Appointment
import com.francisco.proyectofinal.ui.CustomerInfoActivity

class AppointmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAppointmentBinding.bind(view)




    fun render(appointment: Appointment, context: Context) {
        binding.tvDateAppointment.text  = "Día: " + appointment.day
        if(appointment.room==null){
            binding.tVRoomAppointment.text  = "Cuarto: Sin asignar "
        }else{
            binding.tVRoomAppointment.text  = "Cuarto: " + appointment.room
        }
        if(appointment.status==1){
            binding.tvCustomerStatus.text = "Confirmada"
            binding.tvCustomerStatus.setTextColor(Color.parseColor("#00e81e"))
        }
        if(appointment.status==2){
            binding.tvCustomerStatus.text = "Sin confirmar"
            binding.tvCustomerStatus.setTextColor(Color.parseColor("#e8003d"))
        }

        binding.tvCustomerName.text     = appointment.customer.name

//        Glide.with(customerPhoto.context).load(appointment.customer.photo).into(customerPhoto)
//        binding.btnMoreInformation.setOnClickListener{
//            var intent = Intent(context, CustomerInfoActivity::class.java)
//            intent.putExtra("cutomer_id",appointment.customer_id)
//            context.startActivity(intent)
//            (context as Activity).finish()
//
//        }
    }
}