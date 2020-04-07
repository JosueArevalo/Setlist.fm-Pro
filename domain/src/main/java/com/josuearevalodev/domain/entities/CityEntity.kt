package com.josuearevalodev.domain.entities

data class CityEntity(val id: String,
                      val name: String,
                      val state: String,
                      val stateCode: String,
                      val coords: CoordinatesEntity,
                      val country: CountryEntity)
