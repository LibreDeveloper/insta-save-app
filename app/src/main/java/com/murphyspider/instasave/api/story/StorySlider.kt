package com.murphyspider.instasave.api.story

data class StorySlider(
    val height: Double,
    val is_fb_sticker: Int,
    val is_hidden: Int,
    val is_pinned: Int,
    val is_sticker: Int,
    val rotation: Double,
    val slider_sticker: SliderSticker,
    val width: Double,
    val x: Double,
    val y: Double,
    val z: Int
)