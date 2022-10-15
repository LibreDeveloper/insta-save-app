package com.murphyspider.instasave.api.carouselMedia


import com.google.gson.annotations.SerializedName

data class In(
    @SerializedName("duration_in_video_in_sec")
    val durationInVideoInSec: Any,
    @SerializedName("position")
    val position: List<Double>,
    @SerializedName("start_time_in_video_in_sec")
    val startTimeInVideoInSec: Any,
    @SerializedName("user")
    val user: User
)