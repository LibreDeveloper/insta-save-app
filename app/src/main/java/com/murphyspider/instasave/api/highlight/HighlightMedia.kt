package com.murphyspider.instasave.api.highlight

data class HighlightMedia(
    val show_empty_state: Boolean,
    val status: String,
    val tray: List<Tray>
)