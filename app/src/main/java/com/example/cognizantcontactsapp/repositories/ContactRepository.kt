package com.example.cognizantcontactsapp.repositories

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.ContactsContract
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cognizantcontactsapp.models.Contact
import com.example.cognizantcontactsapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ContactRepository {

    val contactListLiveData = MutableLiveData<List<Contact>>()

    @SuppressLint("Range")
    fun getContactListFromDevice(contentResolver: ContentResolver): MutableLiveData<List<Contact>> {
        val contactList : MutableList<Contact> = mutableListOf()
        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        while (phones!!.moveToNext()) {
            val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            val contactModel = Contact(name,phoneNumber)
            contactList!!.add(contactModel)
        }
        contactListLiveData.value = contactList
        phones.close()

        return contactListLiveData
    }

    fun postContactsApiCall(contactsData: List<Contact>, onResult: (List<Contact>?) -> Unit){
        //Get the api function wrapper

        val call = RetrofitClient.apiInterface.postContacts(contactsData)

        //Call the api function
        call.enqueue(
            object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onResult(null) //Execute callback
                }
                override fun onResponse( call: Call<Void>, response: Response<Void>) {
                    onResult(contactsData) //Execute callback
                }
            }
        )

    }

}