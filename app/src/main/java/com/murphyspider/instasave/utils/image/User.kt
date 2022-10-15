package com.murphyspider.instasave.utils.image

data class User(
    val account_badges: List<Any>,
    val follow_friction_type: Int,
    val full_name: String,
    val growth_friction_info: GrowthFrictionInfo,
    val is_private: Boolean,
    val is_verified: Boolean,
    val pk: Long,
    val profile_pic_id: String,
    val profile_pic_url: String,
    val username: String
)