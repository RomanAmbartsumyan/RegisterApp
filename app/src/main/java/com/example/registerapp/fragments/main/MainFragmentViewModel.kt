package com.example.registerapp.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.registerapp.objects.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    application: Application,
    private val repository: MainFragmentRepository
) : AndroidViewModel(application) {

    suspend fun registerUser(user: User): Boolean {
        return repository.registerUser(user)
    }
}