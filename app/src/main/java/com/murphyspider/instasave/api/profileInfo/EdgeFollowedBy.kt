package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class EdgeFollowedBy(
    @SerializedName("count")
    val count: Int
)