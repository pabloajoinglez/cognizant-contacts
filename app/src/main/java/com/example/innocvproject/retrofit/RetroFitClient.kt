package com.example.innocvproject.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val mainServer = "https://hello-world.innocv.com"

    //Http client
    val retrofitClient: Retrofit.Builder = Retrofit.Builder().baseUrl(mainServer).client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())

    //Creates a implementation from the interface
    val apiInterface: RestApiInterface = retrofitClient.build().create(RestApiInterface::class.java)

}