package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.stellar.api.models.AuthRequest
import com.example.stellar.enums.ApiCallTypes
import com.example.stellar.functionalities.AuthFunctionality
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.interfaces.IApiOverrides
import com.example.stellar.interfaces.IAuthFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides

class LoginActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    auth: IAuthFunctionality = AuthFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IApiOverrides,
    IGeneralFunctionality by general,
    IAuthFunctionality by auth {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText

    private lateinit var loginButton: Button
    private lateinit var toRegisterButton: Button

    private lateinit var data: AuthRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.loadViews()
    }

    override fun onStart() {
        super.onStart()

        this.attachListeners()
    }

    override fun onStop() {
        super.onStop()

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
            val isEmailValid = this.validateEmail(this.emailField.text.toString())

            if (!isEmailValid) {
                Toast.makeText(this, R.string.login_invalid_email, Toast.LENGTH_SHORT).show()
            }
            else {
                this.constructObject()
                this.callApi(ApiCallTypes.POST)
            }
        }
    }

    override fun detachListeners() {
        this.toRegisterButton.setOnClickListener(null)
        this.loginButton.setOnClickListener(null)
    }

    override fun constructObject() {
        this.data = AuthRequest(
            email = this.emailField.text.toString(),
            password = this.passwordField.text.toString()
        )
    }

    override fun callApi(type: ApiCallTypes) {
        when (type) {
            ApiCallTypes.POST -> {

            }
            else -> {}
        }
    }
}