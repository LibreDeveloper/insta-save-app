package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class EdgeMutualFollowedBy(
    @SerializedName("count")
    val count: Int,
    @SerializedName("edges")
    val edges: List<Any>
)