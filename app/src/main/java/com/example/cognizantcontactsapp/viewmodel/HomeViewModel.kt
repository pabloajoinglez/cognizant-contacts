package com.example.cognizantcontactsapp.viewmodel

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cognizantcontactsapp.models.Contact
import com.example.cognizantcontactsapp.repositories.ContactRepository

class HomeViewModel : ViewModel() {

    var contactListLiveData: MutableLiveData<List<Contact>>? = null

    fun getContacts(contentResolver: ContentResolver) : LiveData<List<Contact>>? {
        contactListLiveData = ContactRepository.getContactListFromDevice(contentResolver)
        return contactListLiveData
    }

    fun postContacts(contacts : List<Contact>, onResult: (List<Contact>?) -> Unit) {
        ContactRepository.postContactsApiCall(contacts,onResult)
    }

}