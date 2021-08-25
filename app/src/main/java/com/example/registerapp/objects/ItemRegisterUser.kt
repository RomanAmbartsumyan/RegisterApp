package com.example.registerapp.objects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemRegisterUser(
    val request: Request
)