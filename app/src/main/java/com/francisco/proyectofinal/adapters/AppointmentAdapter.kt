package com.francisco.proyectofinal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.model.Appointment

class AppointmentAdapter(private val appointmentList:List<Appointment>) : RecyclerView.Adapter<AppointmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AppointmentViewHolder(layoutInflater.inflate(R.layout.item_appointment,parent,false));
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val item = appointmentList[position]
        holder.render(item,holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }
}