package com.murphyspider.instasave.utils.carousel

data class UserX(
    val account_badges: List<Any>,
    val follow_friction_type: Int,
    val friendship_status: FriendshipStatus,
    val full_name: String,
    val growth_friction_info: GrowthFrictionInfoX,
    val has_anonymous_profile_picture: Boolean,
    val has_highlight_reels: Boolean,
    val has_primary_country_in_feed: Boolean,
    val has_primary_country_in_profile: Boolean,
    val is_favorite: Boolean,
    val is_private: Boolean,
    val is_unpublished: Boolean,
    val is_verified: Boolean,
    val latest_reel_media: Int,
    val pk: Long,
    val profile_pic_id: String,
    val profile_pic_url: String,
    val username: String
)