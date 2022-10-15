package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("user")
    val user: User
)