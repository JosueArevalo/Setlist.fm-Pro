package com.josuearevalodev.setlistfmpro.models.setlistfm.models

data class City(
    val id: String = "",
    val name: String = "",
    val state: String = "",
    val stateCode: String = "",
    val coords: Coordinates = Coordinates(),
    val country: Country = Country()
)
