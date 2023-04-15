package com.example.stellar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.Serializable

class LoginActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    private lateinit var loginButton: Button
    private lateinit var toRegisterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.loadViews()
        this.attachListeners()
    }

    override fun onDestroy() {
        super.onDestroy()

        this.detachListeners()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0,0)
    }

    override fun loadViews() {
        this.emailField = findViewById(R.id.login_email)
        this.passwordField = findViewById(R.id.login_password)
        this.loginButton = findViewById(R.id.login_sign_in)
        this.toRegisterButton = findViewById(R.id.login_to_register)
    }

    override fun setDefaultValues() {}

    override fun attachListeners() {
        this.toRegisterButton.setOnClickListener {
            this.changeActivity(this, RegisterActivity::class.java)
        }

        this.loginButton.setOnClickListener {

        }
    }

    override fun detachListeners() {

    }
}