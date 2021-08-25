package com.example.registerapp.fragments.auth

import android.content.Context
import com.example.registerapp.connection.Api
import com.example.registerapp.objects.AuthUser
import com.example.registerapp.objects.ItemRegisterUser
import com.example.registerapp.objects.Request
import com.example.registerapp.objects.RequestToServer
import com.tree.rh.ctlib.CT
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class AuthFragmentRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val api: Api
) {
    suspend fun checkLogin(authUser: AuthUser): Boolean {
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
            CT.failed2(context, ex.toString())
            false
        }
    }
}