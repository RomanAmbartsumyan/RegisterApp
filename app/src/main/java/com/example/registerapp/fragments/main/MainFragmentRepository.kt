package com.example.registerapp.fragments.main

import com.example.registerapp.objects.User

interface MainFragmentRepository {
    suspend fun registerUser(user: User): Boolean
}