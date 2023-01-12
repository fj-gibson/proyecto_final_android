package com.francisco.proyectofinal.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.francisco.proyectofinal.R
import com.francisco.proyectofinal.databinding.ItemCustomersBinding
import com.francisco.proyectofinal.model.Customer
import com.francisco.proyectofinal.ui.CustomerInfoActivity


class CustomerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCustomersBinding.bind(view)

    val customerName    = view.findViewById<TextView>(R.id.tvCustomerName)
    val customerEmail   = view.findViewById<TextView>(R.id.tvCustomerEmail)
    val customerPhone   = view.findViewById<TextView>(R.id.tvCustomerPhone)
    val customerPhoto   = view.findViewById<ImageView>(R.id.ivCustomerPhoto)


    fun render(customer: Customer,context: Context) {
        customerName.text   = customer.name
        customerEmail.text  = customer.email
        customerPhone.text  = customer.phone
        Glide.with(customerPhoto.context).load(customer.photo).into(customerPhoto)
        binding.btnMoreInformation.setOnClickListener{
            var intent = Intent(context, CustomerInfoActivity::class.java)
            intent.putExtra("cutomer_id",customer.id)
            intent.putExtra("cutomer_name",customer.name)
            intent.putExtra("cutomer_email",customer.email)
            intent.putExtra("cutomer_phone",customer.phone)
            intent.putExtra("cutomer_photo",customer.photo)
            context.startActivity(intent)
            (context as Activity).finish()

        }
    }

}