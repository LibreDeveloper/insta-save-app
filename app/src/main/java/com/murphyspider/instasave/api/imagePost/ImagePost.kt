package com.murphyspider.instasave.api.imagePost

data class ImagePost(
    val auto_load_more_enabled: Boolean,
    val items: List<Item>,
    val more_available: Boolean,
    val num_results: Int,
    val status: String
)