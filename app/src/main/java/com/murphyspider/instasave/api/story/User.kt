package com.murphyspider.instasave.api.story

data class User(
    val account_badges: List<Any>,
    val fan_club_info: FanClubInfo,
    val full_name: String,
    val has_anonymous_profile_picture: Boolean,
    val has_highlight_reels: Boolean,
    val is_favorite: Boolean,
    val is_private: Boolean,
    val is_unpublished: Boolean,
    val is_verified: Boolean,
    val pk: Long,
    val profile_pic_id: String,
    val profile_pic_url: String,
    val transparency_product_enabled: Boolean,
    val username: String
)