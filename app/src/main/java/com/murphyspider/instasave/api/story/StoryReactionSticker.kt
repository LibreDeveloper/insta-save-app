package com.murphyspider.instasave.api.story

data class StoryReactionSticker(
    val height: Double,
    val is_fb_sticker: Int,
    val is_hidden: Int,
    val is_pinned: Int,
    val is_sticker: Int,
    val reaction_sticker: ReactionSticker,
    val rotation: Double,
    val width: Double,
    val x: Double,
    val y: Double,
    val z: Int
)