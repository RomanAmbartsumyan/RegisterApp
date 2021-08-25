package com.example.registerapp.fragments

import android.content.Context
import com.example.registerapp.connection.Api
import com.example.registerapp.objects.ItemRegisterUser
import com.example.registerapp.objects.Request
import com.example.registerapp.objects.RequestToServer
import com.example.registerapp.objects.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainFragmentRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val api: Api
) {
    suspend fun registerUser(user: User) {
        with(user) {
            try {
                api.registerUser(
                    RequestToServer(
                        ItemRegisterUser(
                            request =
                            Request(
                                raw =
                                "{ \"email\":\"$email\", \"firstname\":\"$firstName\", \"lastname\":\"$lastName\", \"password\":\"$password\"}"
                            )
                        )
                    )
                )
            } catch (ex: Exception) {
                println(ex)
            }
        }
    }
}