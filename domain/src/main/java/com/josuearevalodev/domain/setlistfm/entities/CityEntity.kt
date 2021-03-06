package com.josuearevalodev.domain.setlistfm.entities

data class CityEntity(
    val id: String = "",
    val name: String = "",
    val state: String = "",
    val stateCode: String = "",
    val coords: CoordinatesEntity = CoordinatesEntity(),
    val country: CountryEntity = CountryEntity()
)
