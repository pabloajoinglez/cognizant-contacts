package com.example.cognizantcontactsapp.retrofit

import com.example.cognizantcontactsapp.models.Contact
import retrofit2.Call
import retrofit2.http.*

interface RestApiInterface {

    //@GET("/api/User")
    //fun getUsers(): Call<List<Contact>>

    @Headers("Content-Type: application/json")
    @POST("/api/Contact")
    fun postContacts(@Body contactsData: List<Contact>): Call<Void>

    //@DELETE("/api/User/{id}")
    //fun removeUser(@Path("id") id: Int): Call<Void>

    //@GET("/api/Health")
    //fun getHealth(): Call<Void>

}