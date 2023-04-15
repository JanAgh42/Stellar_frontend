package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private val emailField = findViewById<EditText>(R.id.login_email)
    private val passwordField = findViewById<EditText>(R.id.login_password)

    private val loginButton = findViewById<Button>(R.id.login_sign_in)
    private val toRegisterButton = findViewById<Button>(R.id.login_to_register)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}