package com.example.innocvproject.repositories

import androidx.lifecycle.MutableLiveData
import com.example.innocvproject.models.User
import com.example.innocvproject.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    val userListLiveData = MutableLiveData<List<User>>()

    fun getUserListApiCall(): MutableLiveData<List<User>> {
        //Get the api function wrapper
        val call = RetrofitClient.apiInterface.getUsers()

        //Call the api function
        call.enqueue(
            object: Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    //Handle the error
                }
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val userList : MutableList<User> = mutableListOf()
                    for (user in response.body()!!){
                        userList.add(User(user.id,user.name ?: "Username", user.birthdate ?: ""))
                    }
                    userListLiveData.value = userList
                }
            })
        return userListLiveData
    }

    fun addUserApiCall(userData: User, onResult: (User?) -> Unit){
        //Get the api function wrapper
        val call = RetrofitClient.apiInterface.addUser(userData)

        //Call the api function
        call.enqueue(
            object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onResult(null) //Execute callback
                }
                override fun onResponse( call: Call<Void>, response: Response<Void>) {
                    onResult(userData) //Execute callback
                }
            }
        )
    }

    fun removeUserApiCall(id: Int, onResult: (Boolean?) -> Unit){
        //Get the api function wrapper
        val call = RetrofitClient.apiInterface.removeUser(id)

        //Call the api function
        call.enqueue(
            object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onResult(false) //Execute callback
                }
                override fun onResponse( call: Call<Void>, response: Response<Void>) {
                    onResult(true) //Execute callback
                }
            }
        )
    }

    fun getHealthApiCall(onResult: (Boolean?) -> Unit){
        //Get the api function wrapper
        val call = RetrofitClient.apiInterface.getHealth()

        //Call the api function
        call.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false) //Execute callback
            }
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(true) //Execute callback
            }
        })
    }

}