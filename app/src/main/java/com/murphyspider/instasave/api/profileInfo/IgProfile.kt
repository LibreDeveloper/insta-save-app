package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class IgProfile(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)