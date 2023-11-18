package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("id") var id: String?,
    @SerializedName("user_id") var userId: String,
    @SerializedName("message") var message: String,
    @SerializedName("date") var date: String
) : BaseModel
