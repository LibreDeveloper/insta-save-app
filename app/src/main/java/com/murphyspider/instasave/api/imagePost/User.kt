package com.murphyspider.instasave.api.imagePost

data class User(
    val full_name: String,
    val is_private: Boolean,
    val is_verified: Boolean,
    val pk: Long,
    val profile_pic_id: String,
    val profile_pic_url: String,
    val username: String
)