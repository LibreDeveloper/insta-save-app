package com.murphyspider.instasave.api.carouselMedia


import com.google.gson.annotations.SerializedName

data class ImageVersions2(
    @SerializedName("candidates")
    val candidates: List<Candidate>
)