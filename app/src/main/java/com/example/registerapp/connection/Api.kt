package com.example.registerapp.connection

import com.example.registerapp.objects.RequestToServer
import com.example.registerapp.objects.ResponseRegister

import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("registerUser")
    suspend fun registerUser(@Body requestToServer: RequestToServer): ResponseRegister

    @POST("checkLogin")
    suspend fun checkLogin(@Body requestToServer: RequestToServer): ResponseRegister
}