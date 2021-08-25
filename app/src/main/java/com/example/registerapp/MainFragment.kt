package com.example.registerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.registerapp.databinding.FragmentMainBinding
import com.example.registerapp.utils.*
import com.google.android.material.textfield.TextInputLayout

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            button.setOnClickListener {
                val secondNameIsValid =
                    validField(textInputLayoutSecondName)
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

                addTextChangeListener(textInputLayoutSecondName)
                addTextChangeListener(textInputLayoutName)
                addTextChangeListener(textInputLayoutPatronymic)
                addEmailChangeListener(textInputLayoutEmail)
                addTextChangeListener(textInputLayoutPassword)
                addTextChangeListener(textInputLayoutRepeatPassword)
                addRepeatPasswordChangeListener(
                    textInputLayoutPassword,
                    textInputLayoutRepeatPassword
                )
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


