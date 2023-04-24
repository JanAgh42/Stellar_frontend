package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class UserGroup(
    @SerializedName("id") var id: String?,
    @SerializedName("user_id") var userId: String,
    @SerializedName("group_id") var groupId: String,
    @SerializedName("is_owner") var isOwner: Boolean
)
