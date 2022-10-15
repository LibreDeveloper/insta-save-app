package com.murphyspider.instasave.api.carouselMedia


import com.google.gson.annotations.SerializedName

data class VideoVersion(
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)