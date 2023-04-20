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
import com.example.stellar.functionalities.WatcherFunctionality
import com.example.stellar.interfaces.IApiOverrides
import com.example.stellar.interfaces.IAuthFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides

class RegisterActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    auth: IAuthFunctionality = AuthFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IApiOverrides,
    IGeneralFunctionality by general,
    IAuthFunctionality by auth {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var repPasswordField: EditText

    private lateinit var registerButton: Button
    private lateinit var toLoginButton: Button

    private lateinit var data: AuthRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
            val isEmailValid = this.validateEmail(this.emailField.text.toString())
            val isPasswordValid = this.validatePassword(this.passwordField.text.toString())
            val areEqual = this.passwordField.text.toString() == this.repPasswordField.text.toString()

            if (!isEmailValid) {
                Toast.makeText(this, R.string.register_invalid_email, Toast.LENGTH_SHORT).show()
            }
            else if (!isPasswordValid) {
                Toast.makeText(this, R.string.register_invalid_pass, Toast.LENGTH_SHORT).show()
            }
            else if (!areEqual) {
                Toast.makeText(this, R.string.register_not_equal, Toast.LENGTH_SHORT).show()
            }
            else {
                this.constructObject()
                this.callApi(ApiCallTypes.POST)
            }
        }

        this.passwordField.addTextChangedListener(
            WatcherFunctionality(this.passwordField)
        )
    }

    override fun detachListeners() {
        this.toLoginButton.setOnClickListener(null)
        this.registerButton.setOnClickListener(null)
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