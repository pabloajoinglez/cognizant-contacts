package com.example.cognizantcontactsapp.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cognizantcontactsapp.R

class ContactCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var contactName: TextView = itemView.findViewById(R.id.contact_name)
    var contactNumber: TextView = itemView.findViewById(R.id.contact_number)
}