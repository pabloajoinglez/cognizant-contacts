package com.example.innocvproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.innocvproject.repositories.UserRepository

class SettingsViewModel : ViewModel () {

    fun getHealth(onResult: (Boolean?) -> Unit) {
        UserRepository.getHealthApiCall(onResult)
    }
}