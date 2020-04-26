package com.josuearevalodev.domain.setlistfm.entities

data class RemoteArtistSetlistsResponse(val type: String?,
                                        val itemsPerPage: Int?,
                                        val page: Int?,
                                        val total: Int?,
                                        val setlist: List<RemoteSetlistEntity>?)
