package com.josuearevalodev.domain.setlistfm.entities

data class ArtistSetlistsResponse(val type: String,
                                  val itemsPerPage: Int,
                                  val page: Int,
                                  val total: Int,
                                  val setlist: List<SetlistEntity>)
