package com.example.registerapp.utils

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.annotation.LayoutRes
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.registerapp.R
import com.google.android.material.textfield.TextInputLayout

@SuppressLint("ResourceType")
fun Fragment.getStringRes(@LayoutRes res: Int): String {
    return resources.getString(res)
}

fun Fragment.validField(layout: TextInputLayout): Boolean {
    return if (layout.editText?.text!!.isBlank()) {
        layout.error = getString(R.string.required_field)
        false
    } else {
        layout.error = null
        true
    }
}

@SuppressLint("ResourceType")
fun Fragment.validEmail(layout: TextInputLayout): Boolean {
    val text = layout.editText?.text!!
    return if (text.isBlank()) {
        layout.error = getString(R.string.required_field)
        false
    } else {
        if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            layout.error = getStringRes(R.string.incorrect_email)
            false
        } else {
            layout.error = null
            true
        }
    }
}

@SuppressLint("ResourceType")
fun Fragment.addTextChangeListener(layout: TextInputLayout) {
    layout.editText?.doOnTextChanged { text, _, _, _ ->
        if (text!!.isBlank()) {
            layout.error = getStringRes(R.string.required_field)
        } else {
            layout.error = null
        }
    }
}

@SuppressLint("ResourceType")
fun Fragment.addEmailChangeListener(layout: TextInputLayout) {
    layout.editText?.doOnTextChanged { text, _, _, _ ->
        if (text!!.isBlank()) {
            layout.error = getStringRes(R.string.required_field)
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(layout.editText?.text!!).matches()) {
                layout.error = getStringRes(R.string.incorrect_email)
            } else {
                layout.error = null
            }
        }
    }
}

@SuppressLint("ResourceType")
fun Fragment.addRepeatPasswordChangeListener(
    layoutPassword: TextInputLayout,
    layoutRepeatPassword: TextInputLayout
) {
    layoutRepeatPassword.editText?.doOnTextChanged { text, _, _, _ ->
        if (text.toString() != layoutPassword.editText?.text.toString()) {
            layoutRepeatPassword.error = getStringRes(R.string.passwords_are_not_the_same)
        } else {
            layoutRepeatPassword.error = null
        }
    }
}

fun Fragment.getTextFromTextLayout(layout: TextInputLayout): String {
    return layout.editText?.text.toString()
}