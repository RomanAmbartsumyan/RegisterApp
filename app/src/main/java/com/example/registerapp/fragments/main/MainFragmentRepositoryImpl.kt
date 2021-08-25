package com.example.registerapp.fragments.main

import android.content.Context
import com.example.registerapp.connection.Api
import com.example.registerapp.objects.ItemRegisterUser
import com.example.registerapp.objects.Request
import com.example.registerapp.objects.RequestToServer
import com.example.registerapp.objects.User
import com.tree.rh.ctlib.CT
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class MainFragmentRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val api: Api
) : MainFragmentRepository {
    override suspend fun registerUser(user: User): Boolean {
        with(user) {
            return try {
                api.registerUser(
                    RequestToServer(
                        ItemRegisterUser(
                            Request(
                                raw =
                                "{ \"email\":\"$email\", \"firstname\":\"$firstName\", \"lastname\":\"$lastName\", \"password\":\"$password\"}"
                            )
                        )
                    )
                ).isSuccessful
            } catch (ex: Exception) {
                Timber.e(ex)
                CT.failed2(context, ex.toString())
                false
            }
        }
    }
}