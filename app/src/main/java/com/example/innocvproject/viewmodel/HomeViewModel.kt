package com.example.innocvproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innocvproject.models.User
import com.example.innocvproject.repositories.UserRepository

class HomeViewModel : ViewModel() {

    var userListLiveData: MutableLiveData<List<User>>? = null

    fun getUsers() : LiveData<List<User>>? {
        userListLiveData = UserRepository.getUserListApiCall()
        return userListLiveData
    }

}