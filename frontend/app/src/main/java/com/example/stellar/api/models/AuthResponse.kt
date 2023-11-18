package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("user_id") var userId: String,
    @SerializedName("token") var token: String
) : BaseModel
