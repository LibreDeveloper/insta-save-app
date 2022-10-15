package com.murphyspider.instasave.api.story

data class StoriesMedia(
    val ad_expiry_timestamp_in_millis: Any,
    val can_gif_quick_reply: Boolean,
    val can_react_with_avatar: Boolean,
    val can_reply: Boolean,
    val can_reshare: Boolean,
    val expiring_at: Int,
    val has_besties_media: Boolean,
    val has_fan_club_media: Boolean,
    val id: Long,
    val is_cta_sticker_available: Any,
    val items: List<Item>,
    val latest_reel_media: Int,
    val media_count: Int,
    val media_ids: List<Long>,
    val prefetch_count: Int,
    val reel_type: String,
    val seen: Int,
    val status: String,
    val user: UserX
)