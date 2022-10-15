package com.murphyspider.instasave.api.videoPost


import com.google.gson.annotations.SerializedName

data class IgtvFirstFrame(
    @SerializedName("height")
    val height: Int,
    @SerializedName("scans_profile")
    val scansProfile: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)