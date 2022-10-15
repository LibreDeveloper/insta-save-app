package com.murphyspider.instasave.api.videoPost


import com.google.gson.annotations.SerializedName

data class AudioReattributionInfo(
    @SerializedName("should_allow_restore")
    val shouldAllowRestore: Boolean
)