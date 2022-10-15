package com.murphyspider.instasave.api.videoPost


import com.google.gson.annotations.SerializedName

data class AdditionalAudioInfo(
    @SerializedName("additional_audio_username")
    val additionalAudioUsername: Any,
    @SerializedName("audio_reattribution_info")
    val audioReattributionInfo: AudioReattributionInfo
)