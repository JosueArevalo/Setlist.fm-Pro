package com.josuearevalodev.persistence.entities

import androidx.room.Entity

@Entity
data class DatabaseArtistSetlistsResponse(val type: String?,
                                          val itemsPerPage: Int?,
                                          val page: Int?,
                                          val total: Int?,
                                          val setlist: List<DatabaseSetlistEntity>?)
