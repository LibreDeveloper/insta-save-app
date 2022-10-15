package com.murphyspider.instasave.api.story

data class UserX(
    val friendship_status: FriendshipStatus,
    val full_name: String,
    val is_private: Boolean,
    val is_verified: Boolean,
    val pk: Any,
    val profile_pic_id: String,
    val profile_pic_url: String,
    val username: String
)