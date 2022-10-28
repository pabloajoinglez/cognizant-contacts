package com.example.innocvproject.ui

import android.view.View
import com.example.innocvproject.models.User

//Interface used for setting up the onClick behaviour for each User card element on UserCardRecyclerViewAdapter
interface UserListClickListener {
    fun onUserListItemClick(view: View, user: User)
}