package com.murphyspider.instasave.api.imagePost

data class Location(
    val address: String,
    val city: String,
    val external_source: String,
    val facebook_places_id: Long,
    val has_viewer_saved: Boolean,
    val is_eligible_for_guides: Boolean,
    val lat: Double,
    val lng: Double,
    val name: String,
    val pk: Long,
    val short_name: String
)