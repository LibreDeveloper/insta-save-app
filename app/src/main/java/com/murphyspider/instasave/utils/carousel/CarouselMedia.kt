package com.murphyspider.instasave.utils.carousel

data class CarouselMedia(
    val carousel_parent_id: String,
    val commerciality_status: String,
    val id: String,
    val image_versions2: ImageVersions2,
    val is_dash_eligible: Int,
    val media_type: Int,
    val number_of_qualities: Int,
    val original_height: Int,
    val original_width: Int,
    val pk: Long,
    val usertags: Usertags,
    val video_codec: String,
    val video_dash_manifest: String,
    val video_duration: Double,
    val video_versions: List<VideoVersion>
)