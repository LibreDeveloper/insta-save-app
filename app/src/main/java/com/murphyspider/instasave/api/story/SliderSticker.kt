package com.murphyspider.instasave.api.story

data class SliderSticker(
    val background_color: String,
    val emoji: String,
    val question: String,
    val slider_id: Long,
    val slider_vote_average: Double,
    val slider_vote_count: Int,
    val text_color: String,
    val viewer_can_vote: Boolean
)