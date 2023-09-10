package com.example.testing_project_1_adroid_with_nodejs.repository

import com.example.testing_project_1_adroid_with_nodejs.api.RetrofitInstance
import com.example.testing_project_1_adroid_with_nodejs.data.PreferenceProvider
import com.example.testing_project_1_adroid_with_nodejs.model.LoginRequest
import com.example.testing_project_1_adroid_with_nodejs.model.RegisterRequest
import com.example.testing_project_1_adroid_with_nodejs.model.User
import retrofit2.Response


class Repository(private val sharedPreferences: PreferenceProvider) {



    suspend fun login(email : String, password : String) : Response<User> {
        val loginRequest = LoginRequest(email,password)
        return RetrofitInstance.api.login(loginRequest)
    }

    suspend fun register(email : String,username : String ,password : String) : Response<User>{
        val registerRequest = RegisterRequest(email, username, password)
        return RetrofitInstance.api.register(registerRequest)
    }


    fun saveLogged(logged : Boolean){
        sharedPreferences.saveLoggedInStatus(logged)
    }

    fun saveUsername(username : String){
        sharedPreferences.saveUsername(username)
    }

    fun getLogged() = sharedPreferences.isLoggedIn()
    fun getUsername() = sharedPreferences.getUsername()

    fun remove() = sharedPreferences.remove()
}