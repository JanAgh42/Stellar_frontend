package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("user_id") val userId: String,
    @SerializedName("token") val token: String
)
