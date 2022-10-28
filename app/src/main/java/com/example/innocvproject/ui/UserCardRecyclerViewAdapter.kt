package com.example.innocvproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.innocvproject.R
import com.example.innocvproject.models.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserCardRecyclerViewAdapter(private val userList: List<User>,val userListClickListener: UserListClickListener)  : RecyclerView.Adapter<UserCardViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardViewHolder {
            val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)
            return UserCardViewHolder(layoutView)
        }

        override fun onBindViewHolder(holder: UserCardViewHolder, position: Int) {
            if (position < userList.size) {
                //Inflate element card
                val user = userList[position]
                holder.userName.text = user.name
                holder.userBirthdate.text = LocalDateTime.parse(user.birthdate).format(DateTimeFormatter.ISO_DATE)

                //Set onClick behaviour for the card
                holder.itemView.setOnClickListener{
                    userListClickListener.onUserListItemClick(it,userList[position])
                }
            }
        }

        override fun getItemCount(): Int {
            return userList.size
        }

}