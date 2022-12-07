package com.example.cognizantcontactsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cognizantcontactsapp.R
import com.example.cognizantcontactsapp.models.Contact

class ContactCardRecyclerViewAdapter(private val contactList: List<Contact>)  : RecyclerView.Adapter<ContactCardViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactCardViewHolder {
            val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.contact_card, parent, false)
            return ContactCardViewHolder(layoutView)
        }

        override fun onBindViewHolder(holder: ContactCardViewHolder, position: Int) {
            if (position < contactList.size) {
                //Inflate element card
                val con = contactList[position]
                holder.contactName.text = con.name
                holder.contactNumber.text = con.number
            }
        }

        override fun getItemCount(): Int {
            return contactList.size
        }

}