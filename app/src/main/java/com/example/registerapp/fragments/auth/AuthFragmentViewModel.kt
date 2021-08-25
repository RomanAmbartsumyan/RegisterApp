package com.example.registerapp.fragments.auth

import androidx.lifecycle.ViewModel
import com.example.registerapp.objects.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthFragmentViewModel @Inject constructor(private val repository: AuthFragmentRepository) :
    ViewModel() {

    suspend fun checkUser(authUser: AuthUser): Boolean {
        return repository.checkLogin(authUser)
    }
}