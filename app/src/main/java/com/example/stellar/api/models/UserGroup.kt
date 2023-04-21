package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class UserGroup(
    @SerializedName("id") val id: String?,
    @SerializedName("user_id") val userId: String,
    @SerializedName("group_id") val groupId: String,
    @SerializedName("is_owner") val isOwner: Boolean
)
