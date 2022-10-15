package com.murphyspider.instasave.api.story

data class FriendshipStatus(
    val blocking: Boolean,
    val followed_by: Boolean,
    val following: Boolean,
    val incoming_request: Boolean,
    val is_bestie: Boolean,
    val is_feed_favorite: Boolean,
    val is_private: Boolean,
    val is_restricted: Boolean,
    val muting: Boolean,
    val outgoing_request: Boolean
)