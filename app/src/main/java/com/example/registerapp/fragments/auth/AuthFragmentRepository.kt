package com.example.registerapp.fragments.auth

import com.example.registerapp.objects.AuthUser

interface AuthFragmentRepository {
    suspend fun checkLogin(authUser: AuthUser): Boolean
}