package com.murphyspider.instasave.api.postInfo

data class PostInfo(
    val author_id: Any,
    val author_name: String,
    val author_url: String,
    val can_view: Boolean,
    val height: Any,
    val html: String,
    val media_id: String,
    val provider_name: String,
    val provider_url: String,
    val thumbnail_height: Int,
    val thumbnail_url: String,
    val thumbnail_width: Int,
    val title: String,
    val type: String,
    val version: String,
    val width: Int
)