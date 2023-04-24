package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String,
    @SerializedName("owner_id") var ownerId: String,
    @SerializedName("category") var Category: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("banner_color") var bannerColor: String,
    @SerializedName("icon_background_color") var iconBkgColor: String
) : BaseModel
