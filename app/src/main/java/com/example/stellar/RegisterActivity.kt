package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    private val emailField = findViewById<EditText>(R.id.register_email)
    private val passwordField = findViewById<EditText>(R.id.register_password)
    private val repPasswordField = findViewById<EditText>(R.id.register_rep_password)

    private val registerButton = findViewById<Button>(R.id.register_create_account)
    private val toLoginButton = findViewById<Button>(R.id.register_to_login)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}