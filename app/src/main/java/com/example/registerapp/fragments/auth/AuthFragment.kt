package com.example.registerapp.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.registerapp.R
import com.example.registerapp.databinding.FragmentAuthBinding
import com.example.registerapp.objects.AuthUser
import com.example.registerapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val binding: FragmentAuthBinding by viewBinding()
    private val viewModel: AuthFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonAuth.setOnClickListener {
                val emailIsValid = validEmail(textInputLayoutEmail)
                val passwordIsValid = validField(textInputLayoutPassword)

                addEmailChangeListener(textInputLayoutEmail)
                addTextChangeListener(textInputLayoutPassword)

                if (emailIsValid && passwordIsValid) {
                    val email = getTextFromTextLayout(textInputLayoutEmail)
                    val password = getTextFromTextLayout(textInputLayoutPassword)

                    val authUser = AuthUser(email, password)
                    lifecycleScope.launch {
                        val isOk = viewModel.checkUser(authUser)
                        if (isOk) {
                            val action =
                                AuthFragmentDirections.actionAuthFragmentToProfileFragment()
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }
}