package com.example.registerapp.fragments.auth

import android.content.Context
import com.example.registerapp.connection.Api
import com.example.registerapp.objects.AuthUser
import com.example.registerapp.objects.ItemRegisterUser
import com.example.registerapp.objects.Request
import com.example.registerapp.objects.RequestToServer
import com.example.registerapp.utils.toastEx
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class AuthFragmentRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val api: Api
) : AuthFragmentRepository {
    override suspend fun checkLogin(authUser: AuthUser): Boolean {
        return try {
            with(authUser) {
                api.checkLogin(
                    RequestToServer(
                        ItemRegisterUser(
                            Request("{ \"email\":\"$email\", \"password\":\"$password\"}")
                        )
                    )
                ).isSuccessful
            }
        } catch (ex: Exception) {
            Timber.e(ex)
            toastEx(context, ex)
            false
        }
    }
}