package com.example.innocvproject.retrofit

import com.example.innocvproject.models.User
import retrofit2.Call
import retrofit2.http.*

interface RestApiInterface {

    @GET("/api/User")
    fun getUsers(): Call<List<User>>

    @Headers("Content-Type: application/json")
    @POST("/api/User")
    fun addUser(@Body userData: User): Call<Void>

    @DELETE("/api/User/{id}")
    fun removeUser(@Path("id") id: Int): Call<Void>

    @GET("/api/Health")
    fun getHealth(): Call<Void>

}