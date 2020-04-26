package com.josuearevalodev.domain.setlistfm.entities

data class VenueEntity(val id: String = "",
                       val name: String = "",
                       val city: CityEntity = CityEntity(),
                       val url: String = "")
