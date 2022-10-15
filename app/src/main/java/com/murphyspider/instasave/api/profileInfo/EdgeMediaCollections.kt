package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class EdgeMediaCollections(
    @SerializedName("count")
    val count: Int,
    @SerializedName("edges")
    val edges: List<Any>,
    @SerializedName("page_info")
    val pageInfo: PageInfo
)