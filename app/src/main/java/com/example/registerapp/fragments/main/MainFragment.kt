package com.example.registerapp.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.registerapp.R
import com.example.registerapp.databinding.FragmentMainBinding
import com.example.registerapp.objects.User
import com.example.registerapp.utils.*
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register()
    }

    private fun register() {
        with(binding) {
            button.setOnClickListener {
                val secondNameIsValid =
                    validField(textInputLayoutLastName)
                val nameIsValid = validField(textInputLayoutName)
                val patronymicIsValid = validField(textInputLayoutPatronymic)
                val emailIsValid = validEmail(textInputLayoutEmail)
                val passwordIsValid =
                    validField(textInputLayoutPassword) &&
                            validField(textInputLayoutRepeatPassword) &&
                            arePasswordsTheSame(
                                textInputLayoutPassword,
                                textInputLayoutRepeatPassword
                            )

                addTextChangeListener(textInputLayoutLastName)
                addTextChangeListener(textInputLayoutName)
                addTextChangeListener(textInputLayoutPatronymic)
                addEmailChangeListener(textInputLayoutEmail)
                addTextChangeListener(textInputLayoutPassword)
                addTextChangeListener(textInputLayoutRepeatPassword)
                addRepeatPasswordChangeListener(
                    textInputLayoutPassword,
                    textInputLayoutRepeatPassword
                )

                if (secondNameIsValid && nameIsValid && patronymicIsValid && emailIsValid && passwordIsValid) {
                    val email = getTextFromTextLayout(textInputLayoutEmail)
                    val name = getTextFromTextLayout(textInputLayoutName)
                    val lastName = getTextFromTextLayout(textInputLayoutLastName)
                    val password = getTextFromTextLayout(textInputLayoutPassword)

                    val user = User(email, name, lastName, password)
                    lifecycleScope.launch {
                        val isOk = viewModel.registerUser(user)
                        if (isOk) {
                            val action = MainFragmentDirections.actionMainFragment2ToAuthFragment()
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun arePasswordsTheSame(
        passwordLayout: TextInputLayout,
        repeatPasswordLayout: TextInputLayout
    ): Boolean {
        return if (passwordLayout.editText?.text.toString() == repeatPasswordLayout.editText?.text.toString()) {
            true
        } else {
            repeatPasswordLayout.error = getStringRes(R.string.passwords_are_not_the_same)
            false
        }
    }
}


