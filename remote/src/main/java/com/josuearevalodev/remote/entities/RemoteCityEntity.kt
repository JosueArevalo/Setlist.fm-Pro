package com.josuearevalodev.domain.entities

data class RemoteCityEntity(val id: String?,
                            val name: String?,
                            val state: String?,
                            val stateCode: String?,
                            val coords: RemoteCoordinatesEntity?,
                            val country: RemoteCountryEntity?)
