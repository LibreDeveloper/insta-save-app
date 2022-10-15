package com.murphyspider.instasave.utils.video

data class Caption(
    val bit_flags: Int,
    val content_type: String,
    val created_at: Int,
    val created_at_utc: Int,
    val did_report_as_spam: Boolean,
    val is_covered: Boolean,
    val media_id: Long,
    val pk: Long,
    val private_reply_status: Int,
    val share_enabled: Boolean,
    val status: String,
    val text: String,
    val type: Int,
    val user: User,
    val user_id: Long
)