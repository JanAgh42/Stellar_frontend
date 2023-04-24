package com.example.stellar.api.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String,
    @SerializedName("image_url") var imageUrl: String,
    @SerializedName("personal_color") var personalColor: String,
    @SerializedName("groups") var groups: Int,
    @SerializedName("own_groups") var ownGroups: Int,
    @SerializedName("posts") var posts: Int,
    @SerializedName("email") var email: String
) : BaseModel
