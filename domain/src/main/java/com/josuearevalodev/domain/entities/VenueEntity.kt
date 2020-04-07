package com.josuearevalodev.domain.entities

data class VenueEntity(val id: String,
                       val name: String,
                       val city: CityEntity,
                       val url: String)
