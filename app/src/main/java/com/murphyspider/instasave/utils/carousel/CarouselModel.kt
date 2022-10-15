package com.murphyspider.instasave.utils.carousel

data class CarouselModel(
    val auto_load_more_enabled: Boolean,
    val items: List<Item>,
    val more_available: Boolean,
    val num_results: Int,
    val showQRModal: Boolean
)