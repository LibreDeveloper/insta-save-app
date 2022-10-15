package com.murphyspider.instasave.utils.video

data class VideoModel(
    val auto_load_more_enabled: Boolean,
    val items: List<Item>,
    val more_available: Boolean,
    val num_results: Int,
    val showQRModal: Boolean
)