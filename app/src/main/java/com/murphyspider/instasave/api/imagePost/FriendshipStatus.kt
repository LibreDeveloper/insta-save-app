package com.murphyspider.instasave.api.imagePost

data class FriendshipStatus(
    val following: Boolean,
    val is_bestie: Boolean,
    val is_feed_favorite: Boolean,
    val is_restricted: Boolean,
    val outgoing_request: Boolean
)