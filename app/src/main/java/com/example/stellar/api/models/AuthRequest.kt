package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
) : BaseModel