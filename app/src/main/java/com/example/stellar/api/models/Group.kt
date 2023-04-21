package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String,
    @SerializedName("owner_id") val ownerId: String,
    @SerializedName("category") val Category: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("banner_color") val bannerColor: Int,
    @SerializedName("icon_background_color") val iconBkgColor: Int
) : BaseModel
