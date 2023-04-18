package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("id") val id: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("message") val message: String,
    @SerializedName("date") val date: String
)
