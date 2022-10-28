package com.example.innocvproject.viewmodel


import androidx.lifecycle.ViewModel
import com.example.innocvproject.models.User
import com.example.innocvproject.repositories.UserRepository

class AddUserViewModel : ViewModel() {

    fun addUser(name : String, birtdate : String, onResult: (User?) -> Unit) {
        UserRepository.addUserApiCall(User(0,name,birtdate),onResult)
    }
}