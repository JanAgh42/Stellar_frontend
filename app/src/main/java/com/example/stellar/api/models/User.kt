package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("personal_color") val personalColor: Int,
    @SerializedName("groups") val groups: Int
)
