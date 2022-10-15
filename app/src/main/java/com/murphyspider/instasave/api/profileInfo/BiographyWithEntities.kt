package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class BiographyWithEntities(
    @SerializedName("entities")
    val entities: List<Any>,
    @SerializedName("raw_text")
    val rawText: String
)