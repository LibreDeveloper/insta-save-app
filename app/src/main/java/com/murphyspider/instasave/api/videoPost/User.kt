package com.murphyspider.instasave.api.videoPost


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("is_private")
    val isPrivate: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("pk")
    val pk: Any,
    @SerializedName("profile_pic_id")
    val profilePicId: String,
    @SerializedName("profile_pic_url")
    val profilePicUrl: String,
    @SerializedName("username")
    val username: String
)