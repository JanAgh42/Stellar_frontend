package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("user_id") val userId: String,
    @SerializedName("group_id") val groupId: String,
    @SerializedName("reply_to_id") val replyToId: String?,
    @SerializedName("message") val message: String,
    @SerializedName("location") val location: String?,
    @SerializedName("image_url") val imageUrl: String?
)
