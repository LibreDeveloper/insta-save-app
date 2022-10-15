package com.murphyspider.instasave.api.highlight

data class Tray(
    val ad_expiry_timestamp_in_millis: Any,
    val can_gif_quick_reply: Boolean,
    val can_react_with_avatar: Boolean,
    val can_reply: Boolean,
    val can_reshare: Boolean,
    val cover_media: CoverMedia,
    val created_at: Int,
    val id: String,
    val is_converted_to_clips: Boolean,
    val is_cta_sticker_available: Any,
    val is_pinned_highlight: Boolean,
    val items: List<Item>,
    val latest_reel_media: Int,
    val media_count: Int,
    val media_ids: List<Long>,
    val prefetch_count: Int,
    val ranked_position: Int,
    val reel_type: String,
    val seen: Any,
    val seen_ranked_position: Int,
    val title: String,
    val user: UserX
)