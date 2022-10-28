package com.example.innocvproject.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.innocvproject.R

class UserCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var userName: TextView = itemView.findViewById(R.id.user_name)
    var userBirthdate: TextView = itemView.findViewById(R.id.user_birthdate)
}