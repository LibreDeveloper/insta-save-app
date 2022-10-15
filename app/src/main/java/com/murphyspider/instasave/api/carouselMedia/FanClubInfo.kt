package com.murphyspider.instasave.api.carouselMedia


import com.google.gson.annotations.SerializedName

data class FanClubInfo(
    @SerializedName("fan_club_id")
    val fanClubId: Any,
    @SerializedName("fan_club_name")
    val fanClubName: Any
)