package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("end_cursor")
    val endCursor: Any,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean
)