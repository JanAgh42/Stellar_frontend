package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var repPasswordField: EditText

    private lateinit var registerButton: Button
    private lateinit var toLoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
        this.emailField = findViewById(R.id.register_email)
        this.passwordField = findViewById(R.id.register_password)
        this.repPasswordField = findViewById(R.id.register_rep_password)
        this.registerButton = findViewById(R.id.register_create_account)
        this.toLoginButton = findViewById(R.id.register_to_login)
    }

    override fun setDefaultValues() {}

    override fun attachListeners() {
        this.toLoginButton.setOnClickListener {
            this.changeActivity(this, LoginActivity::class.java)
        }

        this.registerButton.setOnClickListener {

        }
    }

    override fun detachListeners() {

    }
}