package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("id") var id: String?,
    @SerializedName("user_id") var userId: String,
    @SerializedName("group_id") var groupId: String,
    @SerializedName("reply_to_id") var replyToId: String?,
    @SerializedName("message") var message: String,
    @SerializedName("location") var location: String?,
    @SerializedName("image_url") var imageUrl: String?
) : BaseModel
