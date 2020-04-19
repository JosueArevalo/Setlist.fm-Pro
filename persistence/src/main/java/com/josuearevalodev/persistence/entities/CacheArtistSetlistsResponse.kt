package com.josuearevalodev.domain.entities

import androidx.room.Entity

@Entity
data class CacheArtistSetlistsResponse(val type: String?,
                                       val itemsPerPage: Int?,
                                       val page: Int?,
                                       val total: Int?,
                                       val setlist: List<CacheSetlistEntity>?)
