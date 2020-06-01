package com.josuearevalodev.setlistfmpro.models.setlistfm.models

data class Venue(
    val id: String = "",
    val name: String = "",
    val city: City = City(),
    val url: String = ""
)
