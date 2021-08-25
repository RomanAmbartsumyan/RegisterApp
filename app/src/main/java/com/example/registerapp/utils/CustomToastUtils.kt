package com.example.registerapp.utils

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import io.github.muddz.styleabletoast.StyleableToast

fun toastEx(context: Context, ex: Exception) {
    StyleableToast.Builder(context)
        .text(ex.toString())
        .length(Toast.LENGTH_LONG)
        .backgroundColor(Color.BLUE)
        .textColor(Color.WHITE)
        .show()
}