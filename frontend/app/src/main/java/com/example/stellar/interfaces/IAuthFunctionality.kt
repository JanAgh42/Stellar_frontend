package com.example.stellar.interfaces

import android.content.Context

interface IAuthFunctionality {
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
}