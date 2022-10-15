package com.murphyspider.instasave.api.imagePost

data class UserX(
    val account_badges: List<Any>,
    val fan_club_info: FanClubInfo,
    val friendship_status: FriendshipStatus,
    val full_name: String,
    val has_anonymous_profile_picture: Boolean,
    val has_highlight_reels: Boolean,
    val is_favorite: Boolean,
    val is_private: Boolean,
    val is_unpublished: Boolean,
    val is_verified: Boolean,
    val latest_reel_media: Int,
    val pk: Any,
    val profile_pic_id: String,
    val profile_pic_url: String,
    val transparency_product_enabled: Boolean,
    val username: String
)