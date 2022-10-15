package com.murphyspider.instasave.utils.video

data class Thumbnails(
    val file_size_kb: Int,
    val max_thumbnails_per_sprite: Int,
    val rendered_width: Int,
    val sprite_height: Int,
    val sprite_urls: List<String>,
    val sprite_width: Int,
    val thumbnail_duration: Double,
    val thumbnail_height: Int,
    val thumbnail_width: Int,
    val thumbnails_per_row: Int,
    val total_thumbnail_num_per_sprite: Int,
    val video_length: Double
)