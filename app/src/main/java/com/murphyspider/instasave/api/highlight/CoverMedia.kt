package com.murphyspider.instasave.api.highlight

data class CoverMedia(
    val crop_rect: List<Double>,
    val cropped_image_version: CroppedImageVersion,
    val media_id: String
)