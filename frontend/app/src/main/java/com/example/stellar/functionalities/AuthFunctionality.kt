package com.example.stellar.functionalities

import android.content.Context
import android.util.Patterns
import com.example.stellar.interfaces.IAuthFunctionality

class AuthFunctionality : IAuthFunctionality {
    override fun validateEmail(email: String): Boolean {
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        return true
    }

    override fun validatePassword(password: String): Boolean {
        if (password.length >= 6
            && password.contains("[A-Z]".toRegex())
            && password.contains("[a-z]".toRegex())
            && password.contains("\\d".toRegex()))
        {
            return true
        }
        return false
    }
}