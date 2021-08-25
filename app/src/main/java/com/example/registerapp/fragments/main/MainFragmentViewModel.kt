package com.example.registerapp.fragments.main

import androidx.lifecycle.ViewModel
import com.example.registerapp.objects.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: MainFragmentRepository
) : ViewModel() {

    suspend fun registerUser(user: User): Boolean {
        return repository.registerUser(user)
    }
}