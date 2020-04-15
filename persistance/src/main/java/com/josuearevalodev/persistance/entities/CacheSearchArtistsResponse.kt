package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheSearchArtistsResponse(val type: String?,
                                      val itemsPerPage: Int?,
                                      val page: Int?,
                                      val total: Int?,
                                      val artist: List<CacheArtistEntity>?)
