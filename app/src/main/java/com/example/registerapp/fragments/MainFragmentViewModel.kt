package com.example.registerapp.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.registerapp.objects.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    application: Application,
    private val repository: MainFragmentRepository
) : AndroidViewModel(application) {

    fun registerUser(user: User) {
        viewModelScope.launch {
            repository.registerUser(user)
        }
    }
}