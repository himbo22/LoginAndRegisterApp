package com.example.testing_project_1_adroid_with_nodejs.api

import com.example.testing_project_1_adroid_with_nodejs.model.LoginRequest
import com.example.testing_project_1_adroid_with_nodejs.model.RegisterRequest
import com.example.testing_project_1_adroid_with_nodejs.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {


    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<User>

    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<User>

}