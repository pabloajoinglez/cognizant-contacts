package com.example.innocvproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innocvproject.models.User
import com.example.innocvproject.repositories.UserRepository

class UserDetailViewModel : ViewModel() {

    val userLiveData = MutableLiveData<User>()

    //Set the user from HomeFragment
    fun setUser(user: User) {
        userLiveData.value = user
    }

    //Get the user from UserDetailFragment
    fun getUser() : LiveData<User>? {
        return userLiveData
    }

    //Remove the user in UserDetailFragment
    fun removeUser(id : Int, onResult: (Boolean?) -> Unit) {
        UserRepository.removeUserApiCall(id,onResult)
    }
}