package com.murphyspider.instasave.api.videoPost


import com.google.gson.annotations.SerializedName

data class MashupInfo(
    @SerializedName("can_toggle_mashups_allowed")
    val canToggleMashupsAllowed: Boolean,
    @SerializedName("formatted_mashups_count")
    val formattedMashupsCount: Any,
    @SerializedName("has_been_mashed_up")
    val hasBeenMashedUp: Boolean,
    @SerializedName("is_creator_requesting_mashup")
    val isCreatorRequestingMashup: Boolean,
    @SerializedName("mashup_type")
    val mashupType: Any,
    @SerializedName("mashups_allowed")
    val mashupsAllowed: Boolean,
    @SerializedName("non_privacy_filtered_mashups_media_count")
    val nonPrivacyFilteredMashupsMediaCount: Int,
    @SerializedName("original_media")
    val originalMedia: Any
)