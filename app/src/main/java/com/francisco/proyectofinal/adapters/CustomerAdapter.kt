package com.francisco.proyectofinal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.model.Customer

class CustomerAdapter(private val customerList:List<Customer>) : RecyclerView.Adapter<CustomerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CustomerViewHolder(layoutInflater.inflate(R.layout.item_customers,parent,false));
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = customerList[position]
        holder.render(item,holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }
}