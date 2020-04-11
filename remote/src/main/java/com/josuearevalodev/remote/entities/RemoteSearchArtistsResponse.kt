package com.josuearevalodev.domain.entities

data class RemoteSearchArtistsResponse(val type: String?,
                                       val itemsPerPage: Int?,
                                       val page: Int?,
                                       val total: Int?,
                                       val artist: List<RemoteArtistEntity>?)
